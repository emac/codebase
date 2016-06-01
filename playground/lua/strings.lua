user_agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X) Excel/14.20.0"
user_agent2 = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; InfoPath.3; ms-office)"

if string.find(user_agent, "Excel") and string.find(user_agent2, "office") then
  print(true)
else
  print(false)
end