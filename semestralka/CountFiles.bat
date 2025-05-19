@echo off
call cloc ./src --exclude-dir=docs --include-lang=Java,Yaml
call cloc ./src --exclude-dir=docs --include-lang=Java,Yaml --md
pause
exit