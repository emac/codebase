object MatchTest2 extends App {
  def matchTest(x: Any) = x match {
    case 1 | "one" => "one"
    case "two" => 2
    case y: Int => "scala.Int"
    case z: String if !z.equals("one") => println(z)
    case _ => "unknown"
  }
  println(matchTest("one"))
  println(matchTest(3))
  println(matchTest("three"))
  println(matchTest(Nil))

  // illegal
//  def matchTest2(x: Option[String], y: Option[String]) = (x, y) match {
//    case (Some(s1), _) | (None, Some(s2)) => println(s1+s2)
//    case _ => println(None)
//  }
//
//  println(Some("s1"), None)
//  println(None, Some("s2"))
//  println(Some("s1"), Some("s2"))
//  println(None, None)

  var st = 'a' #:: 'b' #:: 'c' #:: 'd' #:: Stream.Empty
  st match {
    case first #:: second #:: _ => println(first); println(second)
    case _ =>
  }

  case class Player(name: String, score: Int)
  def currentPlayer(): Player = Player("Daniel", 3500)
  val Player(name, _) = currentPlayer()
  println(name)
  // NPE
//  val Player(name2, _) = Player(null, 3500)
//  println(name2.isEmpty)
}