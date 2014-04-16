# magpie-starter-dropwizard

Micro-service using [dropwizard] (http://dropwizard.io) for use with magpie-angular-starter

***

## Prerequisites
* Maven
* Java 1.7

## Getting Started

Fork the repo

```sh
$ mvn clean install
```
builds the project and

```sh
$ java -jar target/dropwizard-0.0.1-SNAPSHOT.jar server dropwizard.yml
```
runs the project.

Once it is running you can use the [magpie-angular-starter] (http://github.com/cbricker-msw/magpie-angular-starter) project to make requests.

Use magpie/magpie for username and password

## Details
* Requires Basic Authentication from the client
* Doesn't do much other then provide a quick bootstrap for a project
