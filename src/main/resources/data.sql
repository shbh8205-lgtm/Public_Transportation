-- 1. הכנסת קווים (ללא ID)
INSERT INTO "Line" ("number", "source", "destination") 
VALUES ('402', 'Jerusalem', 'Bnei Brak');

INSERT INTO "Line" ("number", "source", "destination") 
VALUES ('161', 'Tel Aviv', 'Bnei Brak');

-- 2. הכנסת אוטובוסים
-- שימי לב: ב-Log שלך העמודה נוצרה כ-"licence_plate" (עם c)
INSERT INTO "Bus" ("licence_plate") VALUES ('12-345-67');

-- 3. הכנסת נהגים
INSERT INTO "Driver" ("name") VALUES ('Israel Israeli');

-- 4. הכנסת נסיעה (Travel)
-- שימי לב: העמודה ב-Log היא "departureTime" (עם T גדולה באמצע)
INSERT INTO "Travel" ("departureTime", "bus_id", "driver_id", "line_id") 
VALUES ('14:30:00', 1, 1, 1);

INSERT INTO "Travel" ("departureTime", "bus_id", "driver_id", "line_id") 
VALUES ('16:00:00', 1, 1, 2);


-- 5. הכנסת תחנות (למשל: ירושלים מרכזית, בני ברק חזון איש)
INSERT INTO "Station" ("name", "number") VALUES ('Jerusalem Central Station', 1001);
INSERT INTO "Station" ("name", "number") VALUES ('Bnei Brak - Hazon Ish', 2002);
INSERT INTO "Station" ("name", "number") VALUES ('Tel Aviv - Savidor', 3003);

-- 6. קישור בין קווים לתחנות (Station_Line)
-- הנחה: line_id=1 הוא קו 402, station_id=1 היא ירושלים, station_id=2 היא בני ברק
-- הוספנו גם את ה-stationOrder כדי שהתחנות יופיעו לפי הסדר
INSERT INTO "Station_Line" ("line_id", "station_id", "stationOrder") VALUES (1, 1, 1);
INSERT INTO "Station_Line" ("line_id", "station_id", "stationOrder") VALUES (1, 2, 2);

-- קישור לקו 161 (line_id=2)
INSERT INTO "Station_Line" ("line_id", "station_id", "stationOrder") VALUES (2, 3, 1);
INSERT INTO "Station_Line" ("line_id", "station_id", "stationOrder") VALUES (2, 2, 2);