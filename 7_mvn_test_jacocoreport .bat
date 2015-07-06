@echo off

rem set DELHEZI_HOME=
rem set JAVA_HOME=
rem set M2_HOME=
set PATH=%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin


rem For a single report.

rem call mvn clean test jacoco:report 
call mvn test jacoco:report 

pause