class Test {
  def test[A](v: A) = v.toString()
}

object Function extends App {
  def apply(f: Int => String, v: Int) = f(v)
  def apply2(f: Char => String, v: Char) = f(v)

  val t = new Test
  apply(t.test, 1)
  apply2(t.test, 'a')

  // 偏函数
  def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }
  val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
    ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil
  println(wordsWithoutOutliers(wordFrequencies))
  // collect = filter & map
  println(wordFrequencies.collect{case(w, f) if f>3 && f<25 => w})
}