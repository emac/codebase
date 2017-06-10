#!/usr/bin/env python
# -*- coding: utf-8 -*-


list1 = [1, 2, 3]
print list1[0]
list1.append(4)
print len(list1)

dict1 = {"a": 1}
dict2 = {"b": 2}
dict3 = dict1, dict2
print dict3[0]['a']

dict3, dict4 = dict3
print dict3
print dict4

set1 = set()
set1.add(1)
set1.add(1)
print len(set1)
