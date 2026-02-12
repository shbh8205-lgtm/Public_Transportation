package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.public_transportation.Models.Station;
import com.example.public_transportation.Repositories.StationRepository;

@Service
public class StationService implements StationServiceInterface {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> getStationById(int id) {
        return stationRepository.findById(id);
    }

    @Override
    public Station addStation(Station station) {
        return stationRepository.save(station);
    }

}
