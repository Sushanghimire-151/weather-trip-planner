# Weather Trip Planner

A Java and Spring Boot learning project demonstrating REST APIs,
database persistence, validation, and exception handling.

## Current implementation

The Trip Service provides:

- GET /api/trips — list trips
- POST /api/trips — create a trip
- PUT /api/trips/{id} — update a trip
- DELETE /api/trips/{id} — delete a trip

## Technologies

- Java 21
- Spring Boot 4.1.2
- Spring Data JPA and Hibernate
- H2 database
- Maven
- Postman

## Repository structure

- trip-service/ — trip CRUD backend

## Run locally

From the repository root:

```bash
cd trip-service
./mvnw spring-boot:run
```

On Windows:

```powershell
cd trip-service
.\mvnw.cmd spring-boot:run
```

The Trip Service runs at http://localhost:8080.

The local H2 database is created when the application starts.
Database files are excluded from Git.

## Planned additions

- Weather Service with Open-Meteo integration
- Location search and weather forecast endpoints
- Communication between services
- React frontend
- PostgreSQL
- Automated tests and GitHub Actions CI