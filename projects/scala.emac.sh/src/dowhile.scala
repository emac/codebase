object DoWhile extends App {
  def _do(body: => Unit) = new _while_(body)
  class _while_(body: => Unit) {
    def _while(cond: => Boolean) {
      body
      if (cond) _while(cond)
    }
  }

  var i = 0
  _do {
    i += 1
    println(i)
  } _while (i < 3)

}