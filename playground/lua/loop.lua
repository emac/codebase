array = {"Lua", "Tutorial"}

if #array == 1 then
  print(1)
elseif #array == 2 then
  print(2)
else
  print("?")
end

for key,value in ipairs(array) 
do
  print(key, value)
end

function square(iteratorMaxCount,currentNumber)
  if currentNumber<iteratorMaxCount
  then
    currentNumber = currentNumber+1
    return currentNumber, currentNumber*currentNumber
  end
end

for i,n in square,3,0
do
  print(i,n)
end

i=0
function inc()
  if (i < 10)
  then
    i = i + 1
    return i
  end
end

for i in inc
do
  print(i)
end

k = 1
function double(j)
  if (k < 10)
  then
    k = k*2
    return k
  end
end

for k in double,1
do
  print(k)
end