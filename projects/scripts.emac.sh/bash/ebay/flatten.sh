#tar -xzf $1
#name=`echo $1 | sed s/.tar.gz//`
#cd $name
#tar -czf geronimo-3.0-SNAPSHOT-bin.tar.gz *
#mv geronimo-3.0-SNAPSHOT-bin.tar.gz ../
#cd ..
#rm -r $name

name=geronimo-3.0-SNAPSHOT-bin
mkdir $name
# uncompress
tar -xzf $1 --directory=$name
cd $name
# copy etc/system.properties.dev into etc/system.properties
rm etc/system.properties
mv etc/system.properties.dev etc/system.properties
# copy var/config/config.xml.dev into var/config/config.xml 
rm var/config/config.xml
mv var/config/config.xml.dev var/config/config.xml
# compress
tar -czf $name.tar.gz *
mv $name.tar.gz ../
# clean
cd ..
rm -r $name