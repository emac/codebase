import scala.collection.mutable

object Generic extends App {

  trait A

  trait B extends A {
    def foo()
  }

  trait C extends B

  def xyz[T <: {def foo()}](t: T) = {
    println(t.getClass)
  }

  def xyz2[T >: B](t: T) = {
    println(t.getClass)
  }

  xyz(new A {
    def foo() = {}
  })

  xyz2(new A {
    def foo() = {}
  })

  // covariant
  case class ListNode[+T](h: T, t: ListNode[T]) {
    def head: T = h

    def tail: ListNode[T] = t

    def prepend[U >: T](elem: U): ListNode[U] =
      ListNode(elem, this)
  }

  val empty: ListNode[Null] = ListNode(null, null)
  println(empty)
  val strList: ListNode[String] = empty.prepend("hello")
    .prepend("world")
  println(strList)
  val anyList: ListNode[Any] = strList.prepend(12345)
  println(anyList)

  // contravariant
  trait Animal

  case class Dog(name: String) extends Animal

  trait PrettyPrinter[-A] {
    def pprint(a: A): String
  }

  def pprint[A](a: A)(implicit p: PrettyPrinter[A]) = p.pprint(a)

  implicit object AnimalPrettyPrinter extends PrettyPrinter[Animal] {
    def pprint(a: Animal) = "[Animal : %s]" format (a)
  }

  println(pprint(Dog("Tom")))

  //
  class A1 {}
  class A2 extends A1 {}

  class MyMap[+T] {
    def put[U >: T](u: U) {}
  }

  val objMap = new MyMap[A1]
  objMap.put(new A2)

  val clzMap = new MyMap[Class[A1]]
  clzMap.put(classOf[A2])
}