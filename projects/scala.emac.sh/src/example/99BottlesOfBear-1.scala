package example

object NinetyNineBottles {
	implicit def intToBottlesInt(i: Int) = new BottlesInt(i)
  
  def main(args: Array[String]) = {
    
    for (verse <- (1 to 99).reverse.map(verse _)) {
      println(verse)
      println
    }
    
    println(line1(0))
    println("Go to the store and buy some more, " + (99 bottles) + " of beer on the wall.")
  }
  
  def verse(n: Int) = line1(n) + '\n' + line2(n)
      
  def line1(n: Int) = 
    (n bottles).capitalize + " of beer on the wall, " + (n bottles) + " of beer."
    
  def line2(n: Int) = 
    "Take one down, pass it around, " + ((n-1) bottles) + " of beer on the wall."
}

class BottlesInt(i: Int) {
  def bottles = i match {
    case 0 => "no more bottles"
    case 1 => "1 bottle"
    case n => n + " bottles"
  }
}