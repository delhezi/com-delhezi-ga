@echo off

rem set DELHEZI_HOME=
rem set JAVA_HOME=
rem set M2_HOME=
set PATH=%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin

call mvn -DskipTests clean package

pause