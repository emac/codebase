# 假设你有一个长条形花坛，但是种花的时候，花不能紧挨在一起，否则都会死。
# 给定一个花坛，为一个数组，0表示空，1表示已经种了花了，请计算出最多还能种多少花。
# 例如：
# 1）输入数组为[1,0,0,0,1]，输出为1。
# 2）输入数组为[0,1,0,0,1,0]，输出为0。

def plant(bed)
  z_count = 0
  f_count = 0
  bed.each do |flag|
    if flag == 1
      z_count = 0
      next
    end
    z_count += 1
    if z_count == 3
      f_count += 1
      z_count = 1
    end
  end
  f_count
end

puts plant([1, 0, 0, 0, 1])
puts plant([0, 1, 0, 0, 1, 0])