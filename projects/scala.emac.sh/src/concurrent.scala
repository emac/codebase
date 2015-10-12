object Concurrent extends App {
  val phs = scala.collection.parallel.mutable.ParHashSet(1 until 5: _*)
  phs foreach println
  
  val numbers = scala.collection.parallel.mutable.ParTrieMap((1 until 100) zip (1 until 100): _*)
  numbers foreach println
}