package com.example.public_transportation.Controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.public_transportation.Models.Driver;
import com.example.public_transportation.Services.DriverServiceInterface;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverServiceInterface driverService;

    public DriverController(DriverServiceInterface driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAll() {
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Driver> getById(@PathVariable Integer id) {
        return driverService.getDriverById(id)
                .map(driver -> ResponseEntity.ok().body(driver))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Driver> add(@RequestBody Driver driver) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.addDriver(driver));
    }

}
