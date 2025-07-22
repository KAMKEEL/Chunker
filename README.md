# Chunkport

**Backport and convert Minecraft worlds across editions**

Chunkport is a Java application built from the
[Chunker](https://github.com/HiveGamesOSS/Chunker) project. This fork extends
the original tool so you can backport worlds from modern Minecraft releases to
1.7.10 and other legacy versions. It also supports conversions between legacy
versions. When provided with a `level.dat` file, Chunkport is able to map
Minecraft block namespaces to modded identifiers using the IDs stored in the
world. The project still retains all of the features of Chunker for converting
between Bedrock and Java editions.

Supported Formats:

- Bedrock
    - 1.12.0
    - 1.13.0
    - 1.14.0 - 1.14.60
    - 1.16.0 - 1.16.220
    - 1.17.0 - 1.17.40
    - 1.18.0 - 1.18.30
    - 1.19.0 - 1.19.80
    - 1.20.0 - 1.20.80
    - 1.21.0 - 1.21.90
- Java
    - 1.7.10
    - 1.8.8
    - 1.9.0 - 1.9.3
    - 1.10.0 - 1.10.2
    - 1.11.0 - 1.11.2
    - 1.12.0 - 1.12.2
    - 1.13.0 - 1.13.2
    - 1.14.0 - 1.14.4
    - 1.15.0 - 1.15.2
    - 1.16.0 - 1.16.5
    - 1.17.0 - 1.17.1
    - 1.18.0 - 1.18.2
    - 1.19.0 - 1.19.4
    - 1.20.0 - 1.20.6
    - 1.21.0 - 1.21.6

**Microsoft Creator Docs:**
https://learn.microsoft.com/en-us/minecraft/creator/documents/chunkeroverview?view=minecraft-bedrock-stable

App Usage
--------

You can find pre-built copies of Chunkport in the releases section of this repository. If you want the original tool you can grab it from the
[Chunker releases page](https://github.com/HiveGamesOSS/Chunker/releases).
Otherwise, see the building section on how to build Chunkport yourself.

Download the appropriate version of the application depending on your operating system and then you will be able to run
the Electron based frontend for Chunkport.

Chunkport defaults to a maximum of 75% of available memory; you can customise this by specifying the amount when launching
Chunkport e.g. `Chunkport.exe -Xmx8G` for 8 gigabytes.

Chunkport forwards `-Xmx` and `-Xms` to the backing JVM. If you wish to supply other options use `--java-options="..."`.

CLI Usage
--------

**Requirements**

- Java 17 or higher

You can find pre-built copies of Chunkport in the releases section of this repository or obtain the original from the
[Chunker releases page](https://github.com/HiveGamesOSS/Chunker/releases).
Otherwise, see the building section on how to build Chunkport yourself.

Chunkport can be run as a command-line application or as a UI. To use Chunkport on the command line run:

`java -jar chunker-cli-VERSION.jar -i "my_world" -f BEDROCK_1_20_80 -o output`

The following parameters are required:

- `-i` / `--inputDirectory` - the path relative to the application which should be used as the input directory.
- `-o` / `--outputDirectory` - the path relative to the application which should be used as the output directory.
- `-f` / `--outputFormat` - the output format to convert the world to in the form `EDITION_X_Y_Z`,
  e.g. `JAVA_1_20_5`, `JAVA_1_7_10`, `BEDROCK_1_19_30`.

Additionally, the following parameters are supported:

- `-m` / `--blockMappings` - a path to a json file or a json object containing block mappings.
- `-sm` / `--simpleBlockMappings` - a path to a text file containing simple mappings in the form `old[state=value] -> new[state=value]`. State values are case sensitive; enumerated values such as directions should be written in upper case (e.g. `facing=WEST`). When provided alongside `--blockMappings` the entries are appended to the JSON mappings.
- `--levelConvert` - when used with `--simpleBlockMappings` resolves the output identifiers using a provided legacy `level.dat` file. Only supported when the destination format is Java 1.12 or lower.
- `--generateSimpleMappingsTemplate` - write an example simple mapping file to the given path and exit.
- For a more comprehensive example containing both JSON and simple mapping formats run `java -cp chunker.jar com.hivemc.chunker.mapping.parser.ComplexMappingsTemplateGenerator <dir>` and the templates will be written to the specified directory.
- `-s` / `--worldSettings` - a path to a json file or a json object containing world settings.
- `-p` / `--pruning` - a path to a json file or a json object containing pruning settings.
- `-c` / `--converterSettings` - a path to a json file or a json object containing converter settings.
- `-d` / `--dimensionMappings` - a path to a json file or a json object containing dimension mappings.
- `-k` / `--keepOriginalNBT` - indicates that NBT should be copied from the input to output where processed by Chunkport,
  this is only supported where the output format is the same as the input and for optimal results you will want to copy
  the input world to the output folder prior to conversion.
- `--enableNEIDs` - enable NotEnoughIDs formatting (the `Blocks16` tag) when converting to legacy Java worlds.
- `--legacySimpleMappings` - apply simple block mappings after flattening using legacy identifiers. When converting to a legacy version with a simple mapping file this is enabled automatically.

You can export settings for your world by using the web interface on `https://chunker.app` through the Advanced
Settings -> Converter Settings tab, the CLI also supports preloading settings from the input directory.

You can also get Chunkport to list available formats by providing an incorrect input,
e.g. `java -jar chunker-VERSION.jar -f ?`.

Building
--------

**Requirements**

- Git
- Java 17 or higher
- Gradle (Optional)

**Note:** Chunkport is split into `app` and `cli`; the app provides an Electron frontend for the application and the cli
is a pure Java application which can be used for conversion or integrated into your own workflows.

**Steps**

1. Clone this repository via `git clone https://github.com/<your-username>/Chunkport.git`.
2. Build the project via `./gradlew build`.
3. Obtain the binary from `build/libs/` (either as a CLI jar, native CLI executable or with the electron frontend).

Chunkport also uses its own fork of a Java LevelDB implementation, https://github.com/HiveGamesOSS/leveldb-mcpe-java/.

Testing
--------

Chunkport attempts to do automated testing where possible to validate data. An example of this is block identifiers being
validated against the palette of the Bedrock and Java, this allows issues with faulty mappings to be identified in the
build process. You can skip tests in the build process by appending `-x test` to the `./gradlew build` command.

Some tests have been excluded from the default test suite marked with the "LongRunning" tag, this is because they can
take several minutes to fully complete.


Currently unsupported features
--------
The following features do not convert (or have limited conversion) when using Chunkport:

- Entities (excluding paintings / item frames).
- Structure data (e.g. Villages / Strongholds).

License and Legal
--------

Chunkport continues to use the MIT license. The upstream
[Chunker](https://github.com/HiveGamesOSS/Chunker/blob/main/LICENSE) project is
also MIT licensed, which permits modification and redistribution and therefore
allows this fork. You can find the full license text in the local
[LICENSE](LICENSE) file.

This project is maintained by Hive Games. This project receives funding from
Mojang Studios. Mojang Studios and its parent company Microsoft assume no
responsibility for the contents of this project.

If you would like to support the original developers, please visit the
[Chunker GitHub repository](https://github.com/HiveGamesOSS/Chunker).

We're hiring!
--------

Join Hive Games, the company that maintains Chunker, 'The Hive' Minecraft featured server, and more!
[Check out our hiring page.](https://jobs.playhive.com/software-engineer-java-186860/)
