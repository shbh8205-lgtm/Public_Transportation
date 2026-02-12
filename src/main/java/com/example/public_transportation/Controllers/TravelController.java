package com.example.public_transportation.Controllers;

import java.time.LocalTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.public_transportation.Models.Travel;
import com.example.public_transportation.Services.BusServiceInterface;
import com.example.public_transportation.Services.DriverServiceInterface;
import com.example.public_transportation.Services.LineServiceInterface;
import com.example.public_transportation.Services.TravelServiceInterface;

@RestController
@RequestMapping("/travels")
public class TravelController {
    private final TravelServiceInterface travelService;
    private final LineServiceInterface lineService;
    private final BusServiceInterface busService;
    private final DriverServiceInterface driverService;

    public TravelController(TravelServiceInterface travelService, BusServiceInterface busService,
            DriverServiceInterface driverService, LineServiceInterface lineService) {
        this.travelService = travelService;
        this.busService = busService;
        this.driverService = driverService;
        this.lineService = lineService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Travel>> getAll() {
        return ResponseEntity.ok().body(travelService.getAllTravels());
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Travel> getById(@PathVariable Integer id) {
        return travelService.getTravelById(id)
                .map(travel -> ResponseEntity.ok().body(travel))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Travel> createTravel(@RequestParam Integer busid, @RequestParam Integer driverid,
            @RequestParam int lineid,@RequestParam LocalTime time) {

        Travel travel = Travel.builder()
                .bus(busService.getBusById(busid).orElseThrow(() -> new RuntimeException("Bus not found")))
                .driver(driverService.getDriverById(driverid)
                        .orElseThrow(() -> new RuntimeException("Driver not found")))
                .line(lineService.getLineById(lineid).orElseThrow(() -> new RuntimeException("Line not found")))
                .departureTime(time)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(travelService.addTravel(travel));
    }
}
