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

## Implemented Technical Specification

I have implemented the following requirements-

### Mandatory Requirements

- Running the tool with no argument will print help message
- Running the tool with file path will process the file (I have tested on html
    and txt file)
- Running the tool with file name will process the file from current directory
- Validates HTTP/HTTPS schemes
- Make network request to validate an URL

### Optional Requirements

- Color output of the validation result
- Running the tool with "-v" will print the version (Currently, the version is
    printed manually)
- Added support for network errors(Timeout, DNS resolution etc)
- Parallel processing of HTTP requests
- Optimized network request by only making HEAD request
- Writing the result to a file for future use

## Under Development

- Process multiple file
- Process files recursively from directory
- Support for glob pattern
- Parallel processing of different tasks, specifically reading from file, making
http request and printing to screen and writing to file

## Contributing

The application has lots of scope to improve. If you want to contribute, you can
do so. Here are some instructions-

This is a Maven project created in Eclipse IDE. You can import it as Maven
project in any IDE. If you are new to IDE, like me, the steps in Eclipse are as follows-

### To Run in Eclipse

- Fork the repo
- Go to your Eclipse IDE
- From File menu import, then select Maven project
- Eclipse will import the project
- Once you play around the code and make some changes
- Right click on the project -> Rus as -> Maven build
- In the configuration window type Goals as "clean compile"
- Then open the terminal in you Ecliplse IDE and write `mvn exec:java
-Dexec.args="the available arguments"
- This should run the aplication

If you don't have Maven in Eclipse, you can read this [https://stackoverflow.com/questions/8620127/maven-in-eclipse-step-by-step-installation](https://stackoverflow.com/questions/8620127/maven-in-eclipse-step-by-step-installation).

### To Run from Terminal (Linux)

First of all you need to have Maven installed in your computer.

- Fork the repo
- Run `mvn clean compile`
- Run `mvn exec:java -Dexec.args="available arguments"
- Done
