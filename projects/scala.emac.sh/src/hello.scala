object Hello extends App {
  val e = new my.Echo
  // e = new my.Echo
  e.echo("world")

  var i = Option.empty[Int]
  var j = Option.empty[Int]
  var i1 = Option[Int](1)
  var j1 = Option[Int](1)
  println (for {
    x <- i
    y <- j
  } yield (x, y))
  println (for {
    x <- i1
    y <- j1
  } yield (x, y))
  println (for {
    x <- i
    y <- j1
  } yield (x, y))

  def hello(firstName: String, middleName: String="b", lastName: String): Unit = {
    println(firstName + middleName + lastName)
  }
  hello("a", lastName="c")

  println(Some(1).map { x => }.map { y => println(y == Unit) }.getOrElse("2"))
}