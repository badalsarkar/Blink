git stash -q --keep-index
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
echo [Checking code formatting]
mvn spotless:check
RESULTS=$?
# Perform checks
if [ $RESULTS -ne 0 ]; then
  echo
  echo
  echo [Error]: Some codes are not formatted properly. 
  echo [Run]: "./mvnw spotless:apply" to auto format codes. Then commit again.
  echo
  exit 1
fi
exit 0
