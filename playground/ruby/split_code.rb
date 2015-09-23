SRC_FILE = 'E:/temp/510000.csv'
TARGET_FILE_TEMPLATE = 'E:/temp/code_%03d.csv'
COUNT_PER_FILE = 1000000

begin
  code_no = 0
  page_no = 1
  page = File.open(sprintf(TARGET_FILE_TEMPLATE, page_no), 'w+')
  puts "open new file: #{page_no}"
  IO.foreach(SRC_FILE) do |row|
    code_no += 1
    # next if code_no==0
    cols = row.split('"')
    # cols[1] = sprintf('%010d', cols[1])
    page.print cols.join('"')
    if code_no % COUNT_PER_FILE == 0
      page.close
      page_no += 1
      page = File.open(sprintf(TARGET_FILE_TEMPLATE, page_no), 'w+')
      puts "open new file: #{page_no}"
    end
  end
  page.close

rescue Exception => e
  puts e.message
ensure

end