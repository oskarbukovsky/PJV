@echo off
rmdir /S /Q "TESTING!!!!" 2>nul
mkdir "TESTING!!!!"
call mvn package -f "pom.xml"
cd "TESTING!!!!"
copy "..\target\semestralka-1.0-SNAPSHOT-jar-with-dependencies.jar" "semestralka-1.0-SNAPSHOT-jar-with-dependencies.jar" /Y >nul
java -jar "semestralka-1.0-SNAPSHOT-jar-with-dependencies.jar"
pause
exit