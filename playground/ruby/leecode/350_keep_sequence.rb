# @param {Integer[]} nums1
# @param {Integer[]} nums2
# @return {Integer[]}
def intersect(nums1, nums2)
  length1 = nums1.length
  length2 = nums2.length
  if length1.zero? || length2.zero?
    return []
  end
  if length1 > length2
    nums1, nums2 = nums2, nums1
    length1 = nums1.length
    length2 = nums2.length
  end
  (0..length1 - 1).each do |i|
    (0..i).each do |j|
      shared = nums1[0 + j, length1 - i]
      (0..length2 - (length1 - i) + 1).each do |k|
        if nums2[k, length1 - i] == shared
          return shared
        end
      end
    end
  end
  []
end

puts intersect([1, 2, 2, 1], [2, 2]).to_s
puts intersect([2, 6, 2, 9, 1], [7, 1]).to_s