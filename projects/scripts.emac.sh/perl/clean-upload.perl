#use 5.012;
use File::stat;
use Time::localtime;

#opendir(my $upload, "C:/apache-tomcat-7.0.11/webapps/usagetrack/upload") || die;
#while(readdir $upload){
#	print "$_\n";
#}
#closedir $upload;

foreach $file (<C:/apache-tomcat-7.0.11/webapps/usagetrack/upload/*>){
	my $lm = ctime(stat($file)->mtime);
	if($lm !~ m/2012/){
		print "last modified time: $lm\n";
		unlink $file;
		print "file deleted: $file\n";
	}
}