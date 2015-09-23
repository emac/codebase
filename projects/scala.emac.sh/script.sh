#!/bin/sh
exec scala "$0" "$@"
!#
object My extends App {
  println("Hello, world!")
}
My.main(args)