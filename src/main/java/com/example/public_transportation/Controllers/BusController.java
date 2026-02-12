package com.example.public_transportation.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.public_transportation.Models.Bus;
import com.example.public_transportation.Services.BusServiceInterface;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/buses")
public class BusController {

    private final BusServiceInterface busService;

    public BusController(BusServiceInterface BusService) {
        this.busService = BusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bus>> getAll() {
        return ResponseEntity.ok().body(busService.getAllBuses());
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Bus> getById(@PathVariable Integer id) {
        return busService.getBusById(id)
                .map(bus -> ResponseEntity.ok().body(bus))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Bus> add(@RequestBody Bus bus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(busService.addBus(bus));
    }

}
