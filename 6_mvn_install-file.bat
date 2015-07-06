@echo off

rem set DELHEZI_HOME=
rem set JAVA_HOME=
rem set M2_HOME=
set PATH=%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin

call mvn install:install-file -DcreateChecksum=true -DgroupId=com.delhezi.ga -DartifactId=com-delhezi-ga -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar -Dfile=target/com-delhezi-ga-1.0.0-SNAPSHOT.jar

pause