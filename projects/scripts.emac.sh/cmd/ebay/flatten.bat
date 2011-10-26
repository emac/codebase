SETLOCAL
set name=geronimo-3.0-SNAPSHOT-bin
mkdir %name%
rem uncompress
7z x -o%name% %1
cd %name%
rem copy etc/system.properties.dev into etc/system.properties
del etc\system.properties
move etc\system.properties.dev etc\system.properties
rem copy var/config/config.xml.dev into var/config/config.xml 
del var\config\config.xml
move var\config\config.xml.dev var\config\config.xml
rem compress
7z a %name%.zip *
move %name%.zip ../
rem clean
cd ..
rd /s /q %name%