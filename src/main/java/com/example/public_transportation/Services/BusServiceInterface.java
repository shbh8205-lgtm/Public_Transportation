package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import com.example.public_transportation.Models.Bus;

public interface BusServiceInterface {
    List<Bus> getAllBuses();

    Optional<Bus> getBusById(int id);

    Bus addBus(Bus bus);
}
