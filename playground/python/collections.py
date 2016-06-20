dict1 = {"a": 1}
dict2 = {"b": 2}
dict3 = dict1, dict2
print dict3[0]['a']

dict3, dict4=dict3
print dict3
print dict4
