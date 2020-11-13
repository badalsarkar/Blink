
mvn spotbugs:check
RESULTS=$?
# Perform checks
git stash pop -q
if [ $RESULTS -ne 0 ]; then
  echo
  echo
  echo [Error]: Lint checks failed. Please fix the issues and commit. 
  echo [Run]: "mvn spotbugs:gui" to view the bugs in GUI mode.
  echo
  exit 1
fi
# You shall commit
echo [Formatting Code]
mvn spotless:apply
git add .
exit 0
