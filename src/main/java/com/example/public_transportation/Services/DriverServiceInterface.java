package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import com.example.public_transportation.Models.Driver;

public interface DriverServiceInterface {
    
    List<Driver> getAllDrivers();

    Optional<Driver> getDriverById(int id);

    Driver addDriver(Driver driver);
}
