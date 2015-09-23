@echo off
setlocal enabledelayedexpansion

pause

rem ------------------------------------
rem 1.Update SybaseV
rem ------------------------------------
if exist D:\SybaseV\WorkSpace\nul (
  rd /s /q D:\SybaseV\WorkSpace
)
mkdir D:\SybaseV\WorkSpace
xcopy D:\Sybase\WorkSpace D:\SybaseV\WorkSpace /s /e

pushd D:\SybaseV\WorkSpace\Eclipse
ren SybaseWorkSpace.bat old.bat
for /f "tokens=*" %%i in (old.bat) do (
  set LINE=%%i
  set LINE=!LINE:\Sybase\=\SybaseV\!
  echo !LINE!>>SybaseWorkSpace.bat
)
del old.bat

cd /d D:\SybaseV\WorkSpace\Eclipse\links
for %%f in (*) do (
  ren %%f old.link
  for /f "tokens=*" %%i in (old.link) do (
    set LINE=%%i
    set LINE=!LINE:/Sybase/=/SybaseV/!
    set LINE=!LINE:\\Sybase\\=\\Sybasev\\!
    echo !LINE!>>%%f
  )
  del old.link
)

cd /d D:\SybaseV\WorkSpace\Eclipse\configuration
for /d %%i in (*) do (
  if "%%i" NEQ ".settings" (
    rd /s /q %%i
  )
)
popd

rem ------------------------------------
rem 2.Import source files into Sybase
rem ------------------------------------

pushd D:\Sybase\WorkSpace\Eclipse
ren plugins plugin.bak
mkdir plugins
xcopy "D:\Installation Files\Dev\eclipse33\Source\plugins" plugins /s /e

ren features features.bak
mkdir features
xcopy "D:\Installation Files\Dev\eclipse33\Source\features" features /s /e

7z x "D:\Installation Files\Dev\eclipse33\3rdPartyPlugins.zip" -o.

cd /d D:\Sybase\WorkSpace\Eclipse\configuration
for /d %%i in (*) do (
  if "%%i" NEQ ".settings" (
    rd /s /q %%i
  )
)
popd