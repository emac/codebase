import scala.concurrent._

import ExecutionContext.Implicits.global

object _Future extends App {
  var f = Future {
    2 / 0
  }
  for (exc <- f.failed) println(exc)

  f = Future {
    4 / 2
  }
  for (exc <- f.failed) println(exc)
}