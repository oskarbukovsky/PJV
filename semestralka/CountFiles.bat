@echo off
call cloc ./src --exclude-dir=docs --include-lang=Java
call cloc ./src --exclude-dir=docs --include-lang=Java --md
pause
exit