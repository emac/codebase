#!/usr/bin/ruby -w

# @param {Integer[][]} a
# @return {Integer[][]}
def transpose(a)
  row_length = a.length
  col_length = a[0].length
  if row_length == col_length
    (0..row_length-1).each {|i|
      (0..i-1).each {|j|
        temp = a[i][j]
        a[i][j] = a[j][i]
        a[j][i] = temp
      }
    }
    a
  else
    b = Array.new(col_length)
    (0..col_length-1).each {|k|
      b[k] = Array.new(row_length)
    }
    (0..row_length-1).each {|i|
      (0..col_length-1).each {|j|
        b[j][i] = a[i][j]
      }
    }
    b
  end
end

begin
  # a = [[1, 2, 3], [4, 5, 6]]
  a = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
  puts transpose(a)
end