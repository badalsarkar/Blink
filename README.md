# Blink

![Java CI with Maven](https://github.com/badalsarkar/Blink/workflows/Java%20CI%20with%20Maven/badge.svg "Java CI with Maven")

Blink is a command line application to detect invalid URLs.

## Available Features

- Check invalid URL with HTTP/HTTPS scheme in a html/txt file
- Color output of validation result
- Use environmental variable to control color print. Set `CLICOLOR=1` for color
print to console.

## How to install

### Download the jar file directly

Download the Blink.jar file from [https://search.maven.org/artifact/ca.badalsarkar/blink](https://search.maven.org/artifact/ca.badalsarkar/blink).

### Download the source code

- Download the zip file from [https://github.com/badalsarkar/Blink/releases](https://github.com/badalsarkar/Blink/releases).
- Unzip the files
- Run `./mvnw clean compile assembly:single`. This will create `Blink.jar` file
inside `target` directory.

### System requirement

- Java version 11 or higher

## Available Options

![Blink Options](./resources/images/blinkOption.png "Blink Options")

## How To Use

**To process a file-**

```bash
java -jar /path/to/Blink.jar/ -s /path/to/the/file
```


**To activate color output-**

```bash
export CLICOLOR=1
java -jar /path/to/Blink.jar -s /path/to/the/file
```

![Blink color output](./resources/gifs/blinkWithColor.gif "Blink color output")

**Pipe text from another process**

```bash
curl -s https://github.com/badalsarkar/Blink | java -jar /path/to/Blink.jar -i
```

![Blink process text input](./resources/gifs/BlinkWIthCurl.gif "Blink processes
    text input")

**To Show Help**

```bash
java - jar /path/to/Blink.jar -h
```

**To Show Version**

```bash
java -jar /path/to/Blink.jar -v
```

## Under Development

- Process multiple file
- Process files recursively from directory
- Support for glob pattern
- Parallel processing of different tasks, specifically reading from file, making
http request and printing to screen and writing to file

## Contributing

Read the contribution [guide](./CONTRIBUTING.md) .
