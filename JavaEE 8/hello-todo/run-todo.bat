REM === SCRIPT VALENDO ===
@echo off

REM ==== Configurar o Java 11 ====
set JAVA_HOME=C:\Users\User\.jdks\corretto-11.0.26
set PATH=%JAVA_HOME%\bin;%PATH%

echo Comando para subir a aplicação:
java -jar pm.jar --deploy target\hello-todo.war --port 8080
pause
