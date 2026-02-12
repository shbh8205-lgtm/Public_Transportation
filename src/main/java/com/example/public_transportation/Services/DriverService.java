package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.public_transportation.Models.Driver;
import com.example.public_transportation.Repositories.DriverRepository;

@Service
public class DriverService implements DriverServiceInterface {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository DriverRepository) {
        this.driverRepository = DriverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(int id) {
        return driverRepository.findById(id);
    }

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
