# Service to display a voting session

See the technical diagram of the architecture: https://www.lucidchart.com/documents/view/8e47ba52-c712-435b-857f-eb50cc42d930/0_0

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

2. Lombok plugin - 1.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/douggass/result-voting-ssessions-service.git
```

**2. Build and run the app using maven**

```bash
cd manage-voting-sessions-service
mvn package
java -jar target/result-voting-ssessions-service-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The server will start at <http://localhost:8080>.

## Exploring the Rest APIs

The application defines following REST APIs

```
1. GET /v1/session/{sessionId}/result - Display the result of a voting session

```

## Running tests

The project also contains tests for all the rules. For running the tests, go to the root directory of the project and type `mvn test` in your terminal.
