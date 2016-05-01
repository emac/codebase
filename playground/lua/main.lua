#!/usr/local/bin/lua

--[[ 
multi-lines comment
]]

print([[Hello,
  world!]])

-- one-line comment

print("Hello".." World!".._VERSION, _ENV)

print(type(_VERSION), type(_ENV))

print(10 ~= 20)

print(#"foo")
