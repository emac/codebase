SETLOCAL
set fullname=%1
set name=%fullname:~0,-4%
rem uncompress
7z x -o%name% %1
rem back up
rem ren %1 %1.bak
rem delete old zip
del %1
rem delete unnecessary profiles
cd %name%
del RideInstaller\EDEProfile.xml
del RideInstaller\TEDProfile.xml
rem compress
7z a %name%.zip *
move %name%.zip ../
rem clean
cd ..
rd /s /q %name%