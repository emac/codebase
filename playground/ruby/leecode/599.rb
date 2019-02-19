# @param {String[]} list1
# @param {String[]} list2
# @return {String[]}
def find_restaurant(list1, list2)
  dict1 = Hash.new(0)
  list1.each_with_index do |s, i|
    dict1[s] += i + 1
  end
  shared = {}
  list2.each_with_index do |s, i|
    if dict1[s] > 0
      shared[s] = dict1[s] + i
    end
  end
  result = []
  min = 2000
  shared.each do |s, i|
    if i < min
      min = i
      result = [s]
    elsif i == min
      result.push(s)
    end
  end
  result
end

puts find_restaurant(["Shogun", "Tapioca Express", "Burger King", "KFC"], ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"])
puts find_restaurant(["Shogun", "Tapioca Express", "Burger King", "KFC"], ["KFC", "Shogun", "Burger King"])
puts find_restaurant(["Shogun","Tapioca Express","Burger King","KFC"], ["KFC","Burger King","Tapioca Express","Shogun"])