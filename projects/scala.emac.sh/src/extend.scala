trait A {
  def value: Int

  def get: Int

  def put(i: Int): A
}

trait B2 extends A {
  abstract override def get = {
    println("B2#get")
    super.get * 2
  }

  abstract override def put(i: Int): A = {
    println("B2#put")
    super.put(i * 2)
    this
  }
}

trait B3 extends A {
  abstract override def get = {
    println("B3#get")
    super.get * 3
  }

  abstract override def put(i: Int): A = {
    println("B3#put")
    super.put(i * 3)
    this
  }
}

class C extends A {
  var value = 1

  override def get = {
    println("C#get")
    value
  }

  override def put(i: Int): A = {
    println("C#put")
    value = i
    this
  }
}

class D1 {
  def a() = {1}
  def b() = {println(a)}
}

class D2 extends D1 {
  override def a() = {2}
}

object Extend extends App {
  println((new C with B2 with B3).put(2).get)
  (new D2).b
}