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
  var st = 'a' #:: 'b' #:: 'c' #:: Stream.Empty
  println(st)

  def fibFrom(a: Int, b: Int): Stream[Int] = a #:: fibFrom(b, a + b)
  println(fibFrom(1, 1).take(7).toList)

  // range
  println(5 to 1 by -1)
  println(1 to 4 by 2)

  // set
  var s1: Set[Any] = Set(1, 2, 3)
  var s2: Set[Any] = Set('a', 'b', 'c')
  println(s1 ++ s2)
  println(s1 & s2)

  // non-strict
  def lazyMap[T, U](coll: Iterable[T], f: T => U) = new Iterable[U] {
    def iterator = coll.iterator map f
  }

  def tran[T, U](t: T) = t.toString()

  var coll = List(1, 2, 3)
  lazyMap(coll, tran) foreach println
}