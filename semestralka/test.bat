@echo off
rmdir /S /Q "TESTING!!!!"
mkdir "TESTING!!!!"
cd "TESTING!!!!"
copy "..\target\semestralka-1.0-SNAPSHOT-jar-with-dependencies.jar" "semestralka-1.0-SNAPSHOT-jar-with-dependencies.jar" /Y > nul
java.exe -jar "semestralka-1.0-SNAPSHOT-jar-with-dependencies.jar"
pause
exit