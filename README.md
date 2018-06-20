# HTTP Server

## Description
A simple HTTP server, built in Java.

## How to Run
Clone the repo, `cd` into it, `gradle build`, `java -jar build/libs/httpServer.jar`.

Supports `-p` and `-d` options for port and directory, respectively. E.g. `java -jar build/libs/httpServer.jar -p 5678 -d /test_dir`. Defaults to `5000` and `/public`.

## How to Test
To run the unit tests, `gradle test`.

To run acceptance tests, clone [cob spec](https://github.com/8thlight/cob_spec), point it at this server and public directory, and run the suite.

## Versions
This server was built with Java 1.8 and JUnit 4.
