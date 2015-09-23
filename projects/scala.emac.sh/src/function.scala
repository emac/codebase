class Test {
  def test[A](v: A) = v.toString()
}

object Function extends App {
  def apply(f: Int => String, v: Int) = f(v)
  def apply2(f: Char => String, v: Char) = f(v)

  val t = new Test
  apply(t.test, 1)
  apply2(t.test, 'a')
}