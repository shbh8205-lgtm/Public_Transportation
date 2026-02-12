package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.public_transportation.Models.Bus;
import com.example.public_transportation.Repositories.BusRepository;

@Service
public class BusService implements BusServiceInterface {
    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(int id) {
        return busRepository.findById(id);
    }

    public Bus addBus(Bus prod) {
        return busRepository.save(prod);
    }

}
