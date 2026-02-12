package com.example.public_transportation.Controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.public_transportation.Models.Station;
import com.example.public_transportation.Services.StationServiceInterface;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationServiceInterface stationService;

    public StationController(StationServiceInterface stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAll() {
        return ResponseEntity.ok().body(stationService.getAllStations());
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Station> getById(@PathVariable Integer id) {
        return stationService.getStationById(id)
            .map(station -> ResponseEntity.ok().body(station))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Station> add(@RequestBody Station station) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.addStation(station));
    }

}
