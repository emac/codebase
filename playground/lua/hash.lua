function hashP2(i)
  return string.sub(math.pow(i+10000, 2), 2, 8)
end

print(hashP2(1))
print(hashP2("1"))