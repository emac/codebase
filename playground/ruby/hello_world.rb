initialized = false

begin
  if (!initialized)
    raise 'An error has occurred.'
  end
  puts "start"
rescue Exception => e
  puts e.message
  puts e.backtrace.inspect

  initialized = true
  retry
else
  puts "succeed"
ensure
  puts "always"
end
puts "end"
