trait t1 {
  def a: t1
}

trait t2 {
  def b: t2
}

trait t3 {
  def c: t3
}

abstract class t12 extends t1 with t2 {
  def a = {
    println("a")
    this
  }
  def b = {
    println("b")
    this
  }
}

class t123 extends t12 with t3 {
  def c = {
    println("c")
    this
  }
}

object Mixin extends App {
  val t = new t123
  t.a.asInstanceOf[t2].b.asInstanceOf[t3].c
  //  ((t123)(((t123)(t.a)).b)).c
}