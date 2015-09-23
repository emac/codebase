object Collection extends App {
  // list
  println('a' :: 'b' :: 'c' :: Nil)
  val l1 = List(1, 2, 3)
  val l2 = List('a', 'b', 'c')
  println(l1 :: l2)
  println(l1 ::: l2)
  
  // set
  var s1: Set[Any] = Set(1, 2, 3)
  var s2: Set[Any] = Set('a', 'b', 'c')
  println(s1 ++ s2)
  println(s1 & s2)
}