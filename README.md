# swapi-cli

A simple CLI written in Kotlin/Native to retrieve resources from The Star Wars API

## Build

Prerequisites:

* Java >= 8
* cURL
* ncurses5-compat-libs

```shell script
./gradlew clean build
```

The binary executable `swapi-cli.kexe` will be generated in the dir `build/bin/native/releaseExecutable`.

## Usage

The CLI expects the following command format:

```shell script
./swapi-cli.kexe [resource] [operation] [param]
```

* `resource` is the API resource to retrieve (people, films, starships, vehicles, species and planets)
* `operation` is the operation to retrieve the resource (count, list, search and get)
* `param` is only required for search and get operations

## Examples

People:

```shell script
./swapi-cli.kexe people count
./swapi-cli.kexe people list
./swapi-cli.kexe people search Luke
./swapi-cli.kexe people get 1
```

Films:

```shell script
./swapi-cli.kexe films count
./swapi-cli.kexe films list
./swapi-cli.kexe films search Hope
./swapi-cli.kexe films get 1
```

Starships:

```shell script
./swapi-cli.kexe starships count
./swapi-cli.kexe starships list
./swapi-cli.kexe starships search Star
./swapi-cli.kexe starships get 1
```

Vehicles:

```shell script
./swapi-cli.kexe vehicles count
./swapi-cli.kexe vehicles list
./swapi-cli.kexe vehicles search Crawler
./swapi-cli.kexe vehicles get 1
```

Species:

```shell script
./swapi-cli.kexe species count
./swapi-cli.kexe species list
./swapi-cli.kexe species search Rod
./swapi-cli.kexe species get 1
```

Planets:

```shell script
./swapi-cli.kexe planets count
./swapi-cli.kexe planets list
./swapi-cli.kexe planets search Yavin
./swapi-cli.kexe planets get 1
```
