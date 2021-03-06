# Contributing

Contribution is welcome. Follow the following steps to setup the project in
linux machine-

1. Fork the repo.
2. Clone it to your computer.
3. Run `chomd +x ./install-prehook.sh`. This will install a git pre-commit hook
   script. The pre-commit hook runs [Spotbugs](https://spotbugs.github.io/) as
   linter and [Google Java Format](https://github.com/google/google-java-format)
   using
   [Spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven#google-java-format)
   for formatting staged code.
4. Make your change.
5. To compile run `./mvnw clean compile`.
6. To run Blink type `./mvnw exec:java -Dexec.args="Available Blink Options"`.
7. Before committing run `./mvnw test`. This will run all the tests. All tests
   must pass.
8. Before you commit run `./mvnw spotless:apply` to apply code formatting.
9. Commit your code. The pre-commit hook, described at step 3 will run. If there
   are any errors fix those and then commit. You can also run `./mvnw clean
   verify` to run code formatting check and linter check before you proceed to
   committing. In that way, you know what to fix before you are ready to commit
   and the commit goes smoothly.
11. Push your changes to GitHub. GitHub Action will run all checks and tests.
12. Once all tests pass open a PR.

## Running Tests

To run all tests run `./mvnw test`. Running tests generates code coverage report
which is located at `./target/site/jacoco-ut/index.html`.

## Adding new tests

Add your tests to `/src/test/` folder. Maintain the current coding style.

## Code Formatting

Blink uses [Google Java
Format](https://google.github.io/styleguide/javaguide.html) for
formatting its code. It is used through a maven plugin [Spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven#google-java-format).
Codes are formatted automatically when you commit them. However, you can also
run the formater manually. Run `./mavenw spotless:apply` to apply formatting.

## Code Linting

Blink uses [Spotbugs](https://spotbugs.github.io/) as linter. It checks for
problems in the code. Again, codes are checked automatically when you try to
commit them. You must run `chmod +x ./install-prehook.sh` to enable this
auto checking. Its a good idea to run the linter from time to time so that you
can detect problems early and fix those. Otherwise, you need to fix when you are
ready to commit.

To run linter type `./mvnw spotbugs:check`. This will report bugs in the terminal.
Once this is done, you can view bugs in GUI mode. Run `./mvnw spotbugs:gui`.
This will open a GUI and you will get all necessary information about bug.

## IDE- Eclipse

The project contains Eclipse setup files. Just import it in Eclipse and
everything will work fine. Make sure, your Eclipse has [maven installed](https://www.toolsqa.com/java/maven/how-to-install-maven-eclipse-ide/).

- Open terminal in eclipse then follow the steps described above.
