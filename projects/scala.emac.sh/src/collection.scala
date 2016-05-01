import scala.collection.JavaConversions._
import scala.collection._

object Collection extends App {
  // list
  println('a' :: 'b' :: 'c' :: Nil)
  val l1 = List(1, 2, 3)
  val l2 = List('a', 'b', 'c')
  println(l1 :: l2)
  println(l1 ::: l2)
  println(l1 :+ 'd')
  println('e' +: l1)

  // stream
  val st = 'a' #:: 'b' #:: 'c' #:: Stream.Empty
  println(st)

  def fibFrom(a: Int, b: Int): Stream[Int] = a #:: fibFrom(b, a + b)

  println(fibFrom(1, 1).take(7).toList)

  // range
  println(5 to 1 by -1)
  println(1 to 4 by 2)

  // set
  val s1: Set[Any] = Set(1, 2, 3)
  val s2: Set[Any] = Set('a', 'b', 'c')
  println(s1 ++ s2)
  println(s1 & s2)

  // non-strict
  def lazyMap[T, U](coll: Iterable[T], f: T => U) = new Iterable[U] {
    def iterator = coll.iterator map f
  }

  def tran[T, U](t: T) = t.toString()

  var coll = List(1, 2, 3)
  lazyMap(coll, tran) foreach println

  // diff
  val d1 = List(1, 2, 3)
  val d2 = List(4, 5, 2)
  println(d1 diff d2)
  println(d2 diff d1)

  val m = Map("a" -> 1, "b" -> 2)
  m.foreach((e: (String, Int)) => {
    println(e._1 + e._2)
  })

  // scala <-> java
  val jList: java.util.List[Int] = Seq(1, 2, 3)
  val sSeq: Seq[Int] = jList
  val jMap: java.util.Map[String, Int] = Map("a"->1)
  val sMap: Map[String, Int] = jMap

  // sliding
  val seq = Range(1, 100).map { i => s"id$i" }
  // work
  seq.sliding(10, 10).foreach(_.foreach(println(_)))
//  seq.sliding(10, 10).foreach( grp => {println(grp.head)})
  // doesn't work
  seq.sliding(10, 10).map(_.foreach(println(_)))
}