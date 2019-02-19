import scala.util.{Failure, Success, Try}

object _Try extends App {

  def wrap(): Try[Try[String]] = Try {
    fail()
  }

  def fail(): Try[String] = Try {
    throw new RuntimeException
  }

  fail() match {
    case Failure(exception) => println(exception)
    case Success(value) => print(value)
  }

  wrap() match {
    case Failure(exception) => println(exception)
    case Success(value) => print(value)
  }

  fail()
}
