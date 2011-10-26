@echo off
setlocal enabledelayedexpansion

rem ------------------------------------
rem @author Emac
rem @version 1.0, 06/20/2007
rem ------------------------------------

rem ------------------------------------
rem 1.Initialize
rem ------------------------------------

pushd c:\
if exist lsco.log (
  del lsco.log
)
if exist lsco.txt (
  del lsco.txt
)
if exist errs.log (
  del errs.log
)
if exist puts.log (
  del puts.log
)

rem ------------------------------------
rem 2.Check validity of paras
rem ------------------------------------

if /i "%1" NEQ "-ws" (
  goto WSException
)
set WS=%2

if "%3" EQU "" (
  set MODE=ALL
) else (
  if /i "%3" EQU "-all" (
    set MODE=ALL
  ) else (
    if /i "%3" EQU "-one" (
	  set MODE=ONE
	  if "%4" EQU "" (
        goto NoFileNameException
      )
      set FILENAME=%4
	) else (
	  goto ModeException
	)
  )
)

rem ------------------------------------
rem 3.1.Get log of lsco cmd
rem ------------------------------------

attcmd -ws !WS! lsco -r -me -fmt "%%n\\n" \calm\dub\sdmp\src >lsco.log

rem ------------------------------------
rem 3.2.Extract list of co-files
rem ------------------------------------

if "!MODE!" EQU "ALL" (
  set NO=0
  for /f %%i in (lsco.log) do (
    set /a NO=!NO!+1
    if !NO! GEQ 3 (
	  set LINE=%%i
	  set LINE=!LINE:~0,-1!
      echo !LINE!>>lsco.txt
    )
  )
) else (
  for /f %%i in ('findstr /i !FILENAME! lsco.log') do (
    set LINE=%%i
	set LINE=!LINE:~0,-1!
    echo !LINE!>>lsco.txt
  )
)

rem ------------------------------------
rem 3.3.Put all co-files
rem ------------------------------------

attcmd -ws !WS! put -ptime @lsco.txt >puts.log
goto Fin

rem ------------------------------------
rem 4.Handle exceptions
rem ------------------------------------

:WSException
echo Error: The 1st parameter should be "-ws" (followed by your workspace name). >errs.log
goto Fin

:ModeException
echo Error: Currently, we only support "-all" and "-one" modes ("-all" is the default mode). >errs.log
goto Fin

:NoFileNameException
echo Error: A co-file name should be given in "-one" mode. >errs.log
goto Fin

rem ------------------------------------
rem 5.Finalize
rem ------------------------------------

:Fin
if exist lsco.log (
  del lsco.log
)
if exist lsco.txt (
  del lsco.txt
)
if exist errs.log (
  notepad errs.log
)
if exist puts.log (
  notepad puts.log
)
popd