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
