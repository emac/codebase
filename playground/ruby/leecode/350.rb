# @param {Integer[]} nums1
# @param {Integer[]} nums2
# @return {Integer[]}
def intersect(nums1, nums2)
  if nums1.empty? || nums2.empty?
    return []
  end
  if nums1.length > nums2.length
    nums1, nums2 = nums2, nums1
  end
  dict1 = Hash.new(0)
  dict2 = Hash.new(0)
  nums1.each { |n| dict1[n] += 1 }
  nums2.each { |n| dict2[n] += 1 }
  shared = []
  dict1.each do |k1, v1|
    v2 = dict2[k1]
    if v2 > 0
      repeat = v2 < v1 ? v2 : v1
      shared += Array.new(repeat, k1)
    end
  end
  shared
end

puts intersect([], []).to_s
puts intersect([1, 2, 2, 1], [2, 2]).to_s
puts intersect([2, 6, 2, 9, 1], [7, 1]).to_s
puts intersect([1, 2], [2, 1]).to_s