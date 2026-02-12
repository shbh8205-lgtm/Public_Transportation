package com.example.public_transportation.Controllers;

import java.time.LocalTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import com.example.public_transportation.Models.Station;
import com.example.public_transportation.Models.Station_Line;
import com.example.public_transportation.Models.Travel;
import com.example.public_transportation.Services.EasyLineService;
// import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/easyline")
public class EasyLineController {

    private final EasyLineService easyLineService;

    public EasyLineController(EasyLineService easyLineService) {
        this.easyLineService = easyLineService;
    }

    // מתי הקו מגיע לתחנה?
    @GetMapping("/findbystation")
    public ResponseEntity<LocalTime> getArrivalTime(@RequestParam Integer stationNumber, @RequestParam String lineNumber) {
        return ResponseEntity.ok(easyLineService.getArrivalTimeByStation(stationNumber, lineNumber));
    }

    // רשימת תחנות בקו
    @GetMapping("/getstationsbyline")
    public ResponseEntity<List<Station_Line>> getStationsByLine(@RequestParam String lineNumber) {
        return ResponseEntity.ok(easyLineService.getStationsByLine(lineNumber));
    }

    // לוח זמנים לפי שעה
    @GetMapping("/gettripsbyhour")
    public ResponseEntity<List<Travel>> getTripsByHour(@RequestParam String hour) {
        // המרה של המחרוזת מה-HTML לאובייקט זמן של Java
        LocalTime time = LocalTime.parse(hour); 
        return ResponseEntity.ok(easyLineService.getTripsByHour(time));
    }

    // הנסיעה האחרונה היום
    @GetMapping("/lasttrip")
    public ResponseEntity<Travel> getLastTrip() {
        return ResponseEntity.ok(easyLineService.getLastTripOfTheDay());
    }

    // כל הנסיעות במערכת
    @GetMapping("/alltrips")
    public ResponseEntity<List<Travel>> getAllTrips() {
        return ResponseEntity.ok(easyLineService.getAllTrips());
    }
}