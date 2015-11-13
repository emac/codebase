object Curry extends App {
  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
  println(nums.head)
  println(nums.tail)

  def add(x:Int)(y:Int):Int = x+y
  val add1 = add (1) _
  println(add1(1))

  val add2 = add (_:Int) (1)
  println(add2(2))
}