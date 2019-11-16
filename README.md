[![CircleCI](https://circleci.com/gh/hubson87/testers-matcher.svg?style=svg)](https://circleci.com/gh/hubson87/testers-matcher)
[![codecov](https://codecov.io/gh/hubson87/testers-matcher/branch/master/graph/badge.svg)](https://codecov.io/gh/hubson87/testers-matcher)

## About the project
In the project I've assumed, that in the future it should be disconnected from the CSV files and connected directly to the database.
That's why I'm initializing the database with inserts at application startup.
I didn't provide any additional profiles, as at the moment it was pointless, because there was no real configuration for the database.

Project structure is really simple, as there was no reason to split controllers into the separate modules in such a small project.

### Operations
1. `/dictionary/all` - returns all dictionaries defined in the application (`countries`, `devices`)
2. `/statistics` - returns bugs related to the users from given countries and devices in the order of bugs count (if parameters are empty, then all values will be returned)

## Additional tools
Project has been connected to CircleCI for continuous integration and codecov, to show the code tests coverage

## Build and run project
To build the project, user needs to have JDK11 installed.
Then he needs to enter the main folder and type:
`mvnw clean package` (it will run maven wrapper and build the application)

Then
`mvnw spring-boot:run`

It will start the spring boot server at port 8080

## FE part
Frontend part has been applied in another project called testers-matcher-fe (https://github.com/hubson87/testers-matcher-fe)
It has been written in the newest Angular version, so end user can interact with the server
