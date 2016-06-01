#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Function definition is here
def printinfo(name, age=35, *vars):
    "This prints a passed info into this function"
    print("Name: ", name)
    print("Age ", age)
    for var in vars:
        print(var)
    return


# Now you can call printinfo function
printinfo(age=50, name="miki")
printinfo(name="miki")
printinfo("miki", 50, "Hello, ", "world!")
