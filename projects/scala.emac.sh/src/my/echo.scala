package my

class Echo {
  def echo(arg: String) {
    println(s"Hello, $arg!")
  }
}

object My extends App {
  new Echo().echo("world")
}