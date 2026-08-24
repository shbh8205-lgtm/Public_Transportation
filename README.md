# Public Transportation 🚌

A Spring Boot system for managing public transportation: buses, drivers, lines, stations, and trips — plus a real-time passenger-facing query module, **EasyLine** (קל-קו), for schedule and arrival-time lookups.

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Key Features](#key-features)
- [Domain Model](#domain-model)
- [Project Structure](#project-structure)
- [REST API](#rest-api)
- [Running Locally](#running-locally)

## Overview

The system models a bus network as **lines**, each connecting an ordered sequence of **stations**, with scheduled **trips** (a bus + driver + departure time on a given line). On top of this, the **EasyLine** module answers real-time passenger questions such as "when does line 5 reach station 12?" or "what's the last trip today?" — calculated on the fly from each trip's departure time and the station's position on the line, rather than pre-stored per-stop times.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.2** – Web MVC, Data JPA, Bean Validation
- **H2** (in-memory database) + H2 Console
- **Lombok**
- **Springdoc OpenAPI** (Swagger UI)
- **Maven**

## Key Features

- 🚍 **Core Entity Management** – CRUD-style endpoints for buses, drivers, lines, stations, and trips.
- 🔗 **Many-to-Many Line/Station Modeling** – A dedicated `Station_Line` join entity with a `stationOrder` field captures each station's position along a specific line, enabling ordered-route queries.
- ⏱️ **Real-Time Arrival Calculation** – Rather than storing a fixed schedule per stop, arrival time at any station is computed dynamically: `departureTime + (stationOrder - 1) minutes`, based on the next upcoming trip on that line.
- 🔍 **Passenger Query Module (EasyLine)**:
  - Arrival time of a specific line at a specific station
  - Full ordered station list for a line
  - Trips filtered by a given hour
  - The last trip of the day
  - All trips currently in the system
- 🖥️ **Built-in Frontend** – A simple RTL Hebrew UI (`static/index.html`) for browsing all entities and running EasyLine queries, served directly by Spring Boot.
- 📑 **Swagger UI** – Auto-generated interactive API docs via Springdoc OpenAPI.

## Domain Model

- **Bus** – a bus vehicle
- **Driver** – a bus driver
- **Line** – a route (number, source, destination)
- **Station** – a stop (number, name)
- **Station_Line** – many-to-many join between lines and stations, ordered via `stationOrder`
- **Travel** – a scheduled trip (line + bus + driver + departure time), with `calculateArrivalTime(stationOrder)` deriving per-stop arrival on the fly

## Project Structure

```
src/main/java/com/example/public_transportation/
├── Controllers/    # REST endpoints (Bus, Driver, Line, Station, Station_Line, Travel, EasyLine)
├── Services/       # Business logic (incl. real-time arrival calculation in EasyLineService)
├── Repositories/   # Spring Data JPA repositories
└── Models/         # JPA entities

src/main/resources/
├── application.properties
├── data.sql            # Seed data (lines, stations, buses, drivers, trips)
└── static/index.html   # Built-in RTL frontend UI

src/test/java/          # Spring Boot test bootstrap
```

## REST API

### Buses – `/buses`
| Method | Path | Description |
|---|---|---|
| GET | `/buses/all` | List all buses |
| GET | `/buses/byid/{id}` | Get bus by ID |
| POST | `/buses/add` | Add a bus |

### Drivers – `/drivers`
| Method | Path | Description |
|---|---|---|
| GET | `/drivers/all` | List all drivers |
| GET | `/drivers/byid/{id}` | Get driver by ID |
| POST | `/drivers/add` | Add a driver |

### Lines – `/lines`
| Method | Path | Description |
|---|---|---|
| GET | `/lines/all` | List all lines |
| GET | `/lines/byid/{id}` | Get line by ID |
| POST | `/lines/add` | Add a line |

### Stations – `/stations`
| Method | Path | Description |
|---|---|---|
| GET | `/stations/all` | List all stations |
| GET | `/stations/byid/{id}` | Get station by ID |
| POST | `/stations/add` | Add a station |

### Travels – `/travels`
| Method | Path | Description |
|---|---|---|
| GET | `/travels/all` | List all trips |
| GET | `/travels/byid/{id}` | Get trip by ID |
| POST | `/travels/add?busid=&driverid=&lineid=&time=` | Create a trip |

### EasyLine (קל-קו) – `/easyline`
Real-time passenger queries:

| Method | Path | Description |
|---|---|---|
| GET | `/easyline/findbystation?stationNumber=&lineNumber=` | When does this line arrive at this station? |
| GET | `/easyline/getstationsbyline?lineNumber=` | Full station list for a line |
| GET | `/easyline/gettripsbyhour?hour=HH:mm` | Trips at a given hour |
| GET | `/easyline/lasttrip` | The last trip of the day |
| GET | `/easyline/alltrips` | All trips in the system |

## Running Locally

Requires **Java 21** and **Maven**.

```bash
mvn spring-boot:run
```

Then open:

- UI: <http://localhost:8080/>
- H2 console: <http://localhost:8080/h2-console>  (JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`, no password)
- Swagger UI: <http://localhost:8080/swagger-ui.html>

The database resets on every startup (`ddl-auto=create-drop`) and is seeded from `data.sql`.

---
Built with Spring Boot, Java 21, and JPA
