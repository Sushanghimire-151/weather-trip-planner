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



## Weather Service

The Weather Service uses Open-Meteo's public Geocoding API
to search for locations.

### Run

```bash
cd weather-service
./mvnw spring-boot:run
```

Runs on port 8081.

### Endpoint

GET /api/locations?name=Boston

Returns matching locations with name, country, latitude,
and longitude.


## API endpoints

| Service | Method | Path | Purpose |
|---|---|---|---|
| Trip | GET | /api/trips | List trips |
| Trip | POST | /api/trips | Create a trip |
| Trip | PUT | /api/trips/{id} | Update a trip |
| Trip | DELETE | /api/trips/{id} | Delete a trip |
| Weather | GET | /api/locations?name=Boston | Search locations |
| Weather | GET | /api/weather?latitude=42.36&longitude=-71.06 | Get a three-day forecast |

Trip Service runs on port 8080.
Weather Service runs on port 8081.

Forecast temperatures use Celsius.
Precipitation probability is expressed as a percentage.

## API testing

Import the collection from the postman directory.
Start the required services before running requests.
Weather requests require internet access.

### Current status

Location search is implemented.
Weather forecasts and communication with the Trip Service
are planned.