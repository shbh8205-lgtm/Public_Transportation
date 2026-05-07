# Public Transportation – מערכת ניהול תחבורה ציבורית

מערכת לניהול תחבורה ציבורית המאפשרת ניהול אוטובוסים, נהגים, קווים, תחנות ונסיעות, וכן שאילתות מידע לנוסע (EasyLine / קל-קו).
A Spring Boot system for managing buses, drivers, lines, stations, and trips, with a passenger information module (**EasyLine**) for real-time schedule queries.

---

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.2** – Web MVC, Data JPA, Validation
- **H2 Database** (in-memory) + H2 Console
- **Lombok**
- **Springdoc OpenAPI** (Swagger UI)
- **Maven**

---

## Project Structure

```
src/main/java/com/example/public_transportation/
├── Controllers/   # REST endpoints
├── Services/      # Business logic
├── Repositories/  # Spring Data JPA repos
└── Models/        # JPA entities
src/main/resources/
├── application.properties
├── data.sql       # Seed data (lines, stations, buses, drivers, trips)
└── static/index.html  # Frontend UI
```

### Domain Model

- **Bus** – אוטובוס
- **Driver** – נהג
- **Line** – קו (number, source, destination)
- **Station** – תחנה (number, name)
- **Station_Line** – טבלת קישור many-to-many בין קווים לתחנות, עם `stationOrder`
- **Travel** – נסיעה (קו + אוטובוס + נהג + שעת יציאה)

---

## Running Locally

Requires **Java 21** and **Maven**.

```bash
mvn spring-boot:run
```

Then open:

- UI: <http://localhost:8080/>
- H2 console: <http://localhost:8080/h2-console>
  (JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`, no password)
- Swagger UI: <http://localhost:8080/swagger-ui.html>

The database is reset on every startup (`ddl-auto=create-drop`) and seeded from `data.sql`.

---

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
שאילתות לנוסע / Passenger queries:

| Method | Path | Description |
|---|---|---|
| GET | `/easyline/findbystation?stationNumber=&lineNumber=` | מתי הקו מגיע לתחנה? |
| GET | `/easyline/getstationsbyline?lineNumber=` | רשימת תחנות בקו |
| GET | `/easyline/gettripsbyhour?hour=HH:mm` | נסיעות בשעה מסוימת |
| GET | `/easyline/lasttrip` | הנסיעה האחרונה היום |
| GET | `/easyline/alltrips` | כל הנסיעות במערכת |

---

## Frontend

`src/main/resources/static/index.html` provides a simple RTL Hebrew UI for browsing all entities and running EasyLine queries. Served automatically at `http://localhost:8080/`.
