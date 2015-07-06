@echo off

rem set DELHEZI_HOME=
rem set JAVA_HOME=
rem set M2_HOME=
set PATH=%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin

rem Kompilacja projektu uwarunkowana spełnieniem wymogów minimalnego pokrycia kodu testami.
call mvn cobertura:check

pause