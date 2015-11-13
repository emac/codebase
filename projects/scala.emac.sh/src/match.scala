object MatchTest2 extends App {
  def matchTest(x: Any) = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
    case z: String => println(z)
    case _ => "unknown"
  }
  println(matchTest(3))
  println(matchTest("three"))
  println(matchTest(Nil))

  var st = 'a' #:: 'b' #:: 'c' #:: 'd' #:: Stream.Empty
  st match {
    case first #:: second #:: _ => println(first); println(second)
    case _ =>
  }

  case class Player(name: String, score: Int)
  def currentPlayer(): Player = Player("Daniel", 3500)
  val Player(name, _) = currentPlayer()
  println(name)
//  val Player(name2, _) = Player(null, 3500)
//  println(name2.isEmpty)
}