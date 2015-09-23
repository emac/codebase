require 'fileutils'

JENKINS_HOME = 'E:/opt/Jenkins'
BACKUP_HOME = 'E:/tmp/Jenkins'

begin
  Dir.foreach("#{JENKINS_HOME}/jobs") do |job|
    puts job
    if('.'==job||'..'==job)
      next
    end
    FileUtils.makedirs("#{BACKUP_HOME}/jobs/#{job}")
    FileUtils.copy("#{JENKINS_HOME}/jobs/#{job}/config.xml", "#{BACKUP_HOME}/jobs/#{job}/config.xml")
  end

rescue Exception => e
  puts e.message
ensure

end