#!/usr/bin/env python
# -*- coding: utf-8 -*-

class Employee:
    'Common base class for all employees'
    empCount = 0

    def __init__(self, name, salary):
        self.name = name
        self.salary = salary
        Employee.empCount += 1

    def displayCount(self):
        print("Total Employee %d" % Employee.empCount)

    def displayEmployee(self):
        print("Name : ", self.name, ", Salary: ", self.salary)


emp = Employee("Emac", 10000)
emp.displayCount()
emp.displayEmployee()

emp.age = 20
print(emp.age)
Employee.age = 20
print(Employee.age)
