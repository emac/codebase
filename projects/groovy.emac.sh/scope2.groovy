#!groovy

s = evaluate(new File('scope.groovy'))
s.hello()
s.hello()