@echo off
setlocal enabledelayedexpansion

cls
echo ====================================================
echo Building account_service...
echo ====================================================
cd /d "%~dp0account_service"
mvn clean package -DskipTests
if errorlevel 1 (
  echo ERROR: Build failed for account_service
  pause
  goto :eof
)
echo Build account_service succeeded.
echo.

echo ====================================================
echo Building product_service...
echo ====================================================
cd /d "%~dp0product_service"
mvn clean package -DskipTests
if errorlevel 1 (
  echo ERROR: Build failed for product_service
  pause
  goto :eof
)
echo Build product_service succeeded.
echo.

echo ====================================================
echo Building auth_service...
echo ====================================================
cd /d "%~dp0auth_service"
mvn clean package -DskipTests
if errorlevel 1 (
  echo ERROR: Build failed for auth_service
  pause
  goto :eof
)
echo Build auth_service succeeded.
