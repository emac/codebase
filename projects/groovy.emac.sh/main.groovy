#!groovy

println 'Groovy' =~ 'Groovy'
println 'Groovy' ==~ 'Groovy'

def mail(from, to) {
    println "From: '$from' To: '$to'"
}

mail 'emac', 'emac'

def hello(firstName, String lastName = null) {
    println "$firstName${lastName == null ? '' : ', ' + lastName}"
}

hello 'emac'
hello 'emac', 'shen'