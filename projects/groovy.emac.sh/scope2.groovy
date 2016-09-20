#!groovy

s = evaluate(new File('scope.groovy'))
s.hello()
s.hello()

a = -2
s.hello()
s.hello()