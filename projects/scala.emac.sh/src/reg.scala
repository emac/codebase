import org.joda.time.DateTime

object Reg extends App {
  val pattern = "Scala".r
  val str = "Scala is Scalable and cool"
  val it = pattern findAllIn str
  for (i <- it) println(i)

  val PATTERN_SEQ = """1(\d{4})(\d{2})(\d{2})(\d{10})""".r
  "1201510280000011111" match {
    case PATTERN_SEQ(year, month, day, id) => println(year, month, day, id)
    case _ => println("Wrong format!")
  }
  "120151028000011111" match {
    case PATTERN_SEQ(year, month, day, id) => println(year, month, day, id)
    case _ => println("Wrong format!")
  }
  val day = new DateTime(2015, 10, 28, 0, 0).toString("yyyyMMdd")
  val id = 11111L
  println(f"1${day}${id}%010d")
  println(f"${id}%010d".toLong)

}