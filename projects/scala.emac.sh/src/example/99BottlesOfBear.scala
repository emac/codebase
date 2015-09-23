package example

import scala.Range

object Bear {
  def main(args: Array[String]) {
    var i = 0
    for (i <- Range(99, 2, -1)) {
      println(s"$i bottles of beer on the wall, $i bottles of beer.\nTake one down and pass it around, ${i - 1} bottles of beer on the wall.\n")
    }
    i = 2
    println(s"$i bottles of beer on the wall, $i bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n")
    i -= 1
    println(s"$i bottle of beer on the wall, $i bottle of beer.\nTake one down and pass it around, no more bottles of beer on the wall.\n")
    println("No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.")
  }
}