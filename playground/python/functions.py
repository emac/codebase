#!/usr/bin/env python
# -*- coding: utf-8 -*-


# Function definition is here
def print_info(name, age=35, *vars):
    "This prints a passed info into this function"
    print("Name: ", name)
    print("Age ", age)
    for var in vars:
        print(var)
    return


# Now you can call printinfo function
print_info(age=50, name="miki")
print_info(name="miki")
print_info("miki", 50, "Hello, ", "world!")
