object Reg extends App {
  val pattern = "Scala".r
  val str = "Scala is Scalable and cool"
  val it = pattern findAllIn str
  for (i <- it) println(i)
}