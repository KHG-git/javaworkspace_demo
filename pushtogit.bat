@echo off
setlocal enabledelayedexpansion

:: 현재 디렉토리 이름을 GitHub 리포지토리 이름으로 사용
for %%I in (.) do set "REPO_NAME=%%~nxI"
set "REPO_DESC=Auto-pushed repository: !REPO_NAME!"
set "PRIVATE=false"

:: GitHub 공개/비공개 설정
if "%PRIVATE%"=="true" (
    set "VISIBILITY=--private"
) else (
    set "VISIBILITY=--public"
)

:: Git 사용자 설정 (KHG-git / kang.hyungoo@gmail.com)
git config user.name >nul 2>&1
if %errorlevel% neq 0 (
    git config --global user.name "KHG-git"
)
git config user.email >nul 2>&1
if %errorlevel% neq 0 (
    git config --global user.email "kang.hyungoo@gmail.com"
)

:: Git 초기화
if not exist ".git" (
    echo Initializing local Git repository...
    git init
    git branch -M master
)

:: 변경된 파일이 있는지 확인
git status --porcelain >temp_git_changes.txt
for /f %%x in (temp_git_changes.txt) do set CHANGES=1
del temp_git_changes.txt

:: 변경 사항 있으면 커밋
if defined CHANGES (
    git add .
    git commit -m "Auto commit - %DATE% %TIME%"
) else (
    echo No changes to commit.
)

:: 원격 저장소 연결 여부 확인
git remote get-url origin >nul 2>&1
if %errorlevel% neq 0 (
    echo Creating GitHub repository: !REPO_NAME!
    gh repo create "KHG-git/!REPO_NAME!" !VISIBILITY! --description "!REPO_DESC!" --source=. --remote=origin --push
) else (
    git push origin master
)

endlocal
