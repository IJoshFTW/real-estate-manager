cd ..
setlocal
for %%i in ("%~dp0..") do set "folder=%%~fi"
echo %folder%
"C:\Program Files (x86)\GourceLIVE\gource-live.sh" %folder%
pause