object Hello extends App {
  val vs1 = Seq("1.0", "1.1", "2.0")
  val vs2 = Seq("a.o", "a.b", "b.o")
  for(v1 <- vs1; v2 <- vs2 if v1 != v2) yield {
    println(v1)
    println(v2)
  }

  val s1 = "abc"
  println(s1 == "abc")

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

  println(Option("String"))
  println(None)

  "1 1 1".split(" ").foreach(println)
}