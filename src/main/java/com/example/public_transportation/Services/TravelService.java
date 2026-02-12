package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.public_transportation.Models.Travel;
import com.example.public_transportation.Repositories.TravelRepository;

@Service
public class TravelService implements TravelServiceInterface {

    private final TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Override
    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    @Override
    public Optional<Travel> getTravelById(int id) {
        return travelRepository.findById(id);
    }

    @Override
    public Travel addTravel(Travel travel) {
        return travelRepository.save(travel);
    }

}
