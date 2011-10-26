#!/bin/sh
name=temp
# uncompress
mkdir $name
unzip $1 -d $name
# delete old zip
rm $1
# delete unnecessary profiles
cd $name
rm RideInstaller/EDEProfile.xml
rm RideInstaller/TEDProfile.xml
rm RideInstaller/RIDEProfile.xml
# rm RideInstaller/RIDE-MAVENProfile.xml
# restore -x permission of installer (Linux)
if test -e RideInstaller/installer
then
chmod a+x RideInstaller/installer
fi
# restore -x permission of installer (Mac)
if test -e RideInstaller/installer.app/Contents/MacOS/installer
then
chmod a+x RideInstaller/installer.app/Contents/MacOS/installer
fi
# compress
zip -r $1 *
mv $1 ..
# clean
cd ..
rm -rf $name
