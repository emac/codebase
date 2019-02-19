# @param {String} s
# @return {String}
def decode_string(s)
  result = ''
  pos = 0
  repeat = 0
  while pos < s.length
    c = s[pos]
    if c == '['
      left_bracket = pos
      # find matching right bracket
      depth = 1
      right_bracket = left_bracket + 1
      while right_bracket < s.length
        c2 = s[right_bracket]
        if c2 == '['
          depth += 1
        elsif c2 == ']'
          depth -= 1
        end
        break if depth == 0
        right_bracket += 1
      end
      result += decode_string(s[left_bracket + 1, right_bracket - left_bracket - 1]) * repeat
      pos = right_bracket + 1
      repeat = 0
    else
      unless c =~ /[[:digit:]]/
        result += c
      else
        repeat = repeat.zero? ? c.to_i : (repeat * 10 + c.to_i)
      end
      pos += 1
    end
  end
  result
end

puts decode_string('3[a]2[bc]')
puts decode_string('3[a2[c]]')
puts decode_string('10[a]')