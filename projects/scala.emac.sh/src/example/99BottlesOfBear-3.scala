package example

import io.Source
// Lazy, brittle "rob the store" version
object GrabbaBeer extends App {
  val buf = new StringBuilder; 
  for (c <- Source.fromURL("http://99-bottles-of-beer.net/lyrics.html")) buf.append(c)
  val Pat = "<p>(.*?)<br/?>\\s*(.*?)</p>".r
  for (Pat(l1, l2) <- Pat findAllIn buf) println(l1 + "\n" + l2 + "\n")
}

// Law-abiding concise version.
object CaseOfBeers extends App {
  def qty(n: Int) = (n match {case 0 => "no more bottles" case 1 => "1 bottle" case n => n + "bottles"}) + " of beer"

  for (b <- 99 to 0 by -1)
    println(qty(b).capitalize + " on the wall, " + qty(b) + "." +
          (if (b > 0) "\nTake one down and pass it around, " + qty(b - 1) + " on the wall.\n"
           else "\nGo to the store and buy more beer, " + qty(99) + " on the wall."))
}
