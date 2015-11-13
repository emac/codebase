object enum extends App {
  object E extends Enumeration {
    type E = Value

    val A = Value(1, "a")
    val B = Value(2, "b")
  }

  val e1 = E.A
  e1 match {
    case E.A => println(e1.toString)
    case E.B => println(e1.toString)
  }
}