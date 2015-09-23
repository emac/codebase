object _Array extends App {
  var my = Array.ofDim[Int](3, 3)
  my(0)(0) = 1
  println(my.size)
  println(my(0).size)
}