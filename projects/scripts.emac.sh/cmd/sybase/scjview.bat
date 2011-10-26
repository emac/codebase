@echo off
setlocal enabledelayedexpansion

rem ------------------------------------
rem  added by emac
rem ------------------------------------
rem ------------------------------------
rem 1.set SYBROOT
rem ------------------------------------

set SYBROOT=%cd%
set SYBROOT_JPR=!SYBROOT:^\=^\^\!

rem ------------------------------------
rem 2.1.update .\ASA9\asa.jpr
rem ------------------------------------
set EMAC=

pushd ASA9
erase asa.jpr
for /f %%i in ('findstr /i "#" asa_nodelete.jpr') do (
    set LINE=%%i
    set LINE=!LINE:#=%SYBROOT_JPR%!
    if not defined EMAC (
        set PluginFile=!LINE:~11!
        set PluginFile > asa.jpr
        set EMAC=EMAC
    ) else (
        set AdditionalClasspath=!LINE:~20!
        set AdditionalClasspath >> asa.jpr
    )
)
popd

rem ------------------------------------
rem 2.2.update .\ASE15\ase.jpr
rem ------------------------------------
set EMAC=

pushd ASE15
erase ase.jpr
echo PluginClass=com.sybase.aseplugin.ASEPlugin > ase.jpr
for /f %%i in ('findstr /i "#" ase_nodelete.jpr') do (
    set LINE=%%i
    set LINE=!LINE:#=%SYBROOT_JPR%!
    if not defined EMAC (
        set PluginFile=!LINE:~11!
        set PluginFile >> ase.jpr
        set EMAC=EMAC
    ) else (
        set AdditionalClasspath=!LINE:~20!
        set AdditionalClasspath >> ase.jpr
    )
)
popd

rem ------------------------------------
rem 2.3.update .\IQ127\iq.jpr
rem ------------------------------------
set EMAC=

pushd IQ127
erase iq.jpr
for /f %%i in ('findstr /i "#" iq_nodelete.jpr') do (
    set LINE=%%i
    set LINE=!LINE:#=%SYBROOT_JPR%!
    if not defined EMAC (
        set PluginFile=!LINE:~11!
        set PluginFile > iq.jpr
        set EMAC=EMAC
    ) else (
        set AdditionalClasspath=!LINE:~20!
        set AdditionalClasspath >> iq.jpr
    )
)
popd
 
rem --------------------------------------------------------------------
rem  Set JDK_DIR to the root of your JDK 1.4.1_02 folder
rem  Set SCROOT to the root of the Sybase Central Development Kit
rem --------------------------------------------------------------------

if "%SYBROOT%" == "" goto no_sybroot 

set JDK_DIR=%SYBROOT%\Shared\Sun\jre142
set SCROOT="%SYBROOT%\Shared\Sybase Central 4.3"
 
rem -----------------------------------------------------------------
rem     You don't have to change anything below this line
rem -----------------------------------------------------------------
 
set CLASSPATH=%SCROOT%\sybasecentral.jar
set CLASSPATH=%CLASSPATH%;%SCROOT%\jcchart400K.jar
set CLASSPATH=%CLASSPATH%;"%SYBROOT%"\Shared\java\jsyblib142.jar
set CLASSPATH=%CLASSPATH%;"%SYBROOT%"\Shared\java\helpmanager11.jar
set CLASSPATH=%CLASSPATH%;"%SYBROOT%"\Shared\java\SCEditor142.jar
set CLASSPATH=%CLASSPATH%;"%SYBROOT%"\Shared\Sun\JavaHelp-1_1\jh.jar

set startclass=com.sybase.central.viewer.SybaseCentral
rem -----------------------------------------------------------------
rem   If you prefer a different look and feel then the default, you are welcome to 
rem   uncomment one of the following SC_LAF settings.  
rem
rem   (Windows only)
rem   set SC_LAF=-Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel
rem   (Mac only)
rem   set SC_LAF=-Dswing.defaultlaf=javax.swing.plaf.mac.MacLookAndFeel
rem
rem   (Multi Platform)
rem   set SC_LAF=-Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel
rem   set SC_LAF=-Dswing.defaultlaf=com.sun.java.swing.plaf.motif.MotifLookAndFeel
rem 
rem -----------------------------------------------------------------
pushd %SCROOT%

"%JDK_DIR%\bin\java" -Xms50m -Xmx500m -Dsun.java2d.noddraw=true -DSYBROOT="%SYBROOT%" -Djava.security.policy=%SCROOT%\SybaseCentral.policy -DSCROOT=%SCROOT% %SC_LAF% -Dsun.java2d.d3d=false -Dsybase.jsyblib.dll.location=%SCROOT%\..\win32 -cp %CLASSPATH% %startclass% -installdir=%SCROOT% -screpository=%SCROOT% -lang=EN

popd
goto end
 
:no_sybroot
echo The SYBROOT environment variable is not set.  This can be set by 
echo executing the SYBASE.bat script file before running this program. 
 
:end 
