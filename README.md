# URLChecker

URLChecker is a command line application to detect invalid urls.

## Available Features

- Check invalid URL with HTTP/HTTPS scheme in a html/txt file
- Save the result to a file
- Color output of validation result

## Available Options

| Options                     | Action                      |
|:----------------------------|:----------------------------|
| -v,--version                | Prints version number       |
| -s,--source<file path>      | Process the file            |
| -d,--destination<file path> | Save the result to the file |
| -h,--hep                    |  Shows help                 |

## How To Use

The executable file is inside the release folder.

**To process a file-**

```bash
java -jar urlchecker.jar -s source/file/path -d
destination/file/if/you/want/to/save/output
```

**To Show Help**

```bash
java - jar urlchecker.jar -h
```

**To Show Version**

```bash
java -jar urlchecker.jar -v
```

## Under Development

- Process multiple file
- Process files recursively from directory
- Support for glob pattern
- Parallel processing of different tasks, specifically reading from file, making
http request and printing to screen and writing to file

## Contributing

Contribution is welcome. The application has lots of scope to improve. If you
find one, you can contribute.

### Setting up the project for testing

This is a Maven project created in Eclipse IDE. If you have Eclipse IDE, its
very simple.

**Setup in Eclipse**

Prerequisite: Eclipse should have Maven installed.

- Fork the repository
- Clone the repository to your local machine
- Import the project in your Eclipse IDE
- Play around with the code
- To compile the project select the Run menu -> Rus as -> Maven Clean
- Select Run menu -> Run as -> Maven Build
- If you get Edit Configuration window, put "clean compile" in the Goals field
and click Run. This will compile the code.
- Now to run the code, open terminal in Eclipse
- Run command `mvn exec:java -Dexec.args="available options"
- The app will run now

**Setup in Other IDE**

It should be fairly easy. Just check how to import Maven project in your IDE. I
have not used other IDEs. You can Google it.

**Setup in Terminal(Linux)**

Prerequisite: Maven must be installed in your computer

- Fork and clone the repo
- To compile the code, run `mvn clean compile` 
- To initiate the application, run `mvn exec:java -Dexec.args="application available options"`
