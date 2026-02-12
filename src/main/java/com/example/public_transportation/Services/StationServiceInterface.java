package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import com.example.public_transportation.Models.Station;

public interface StationServiceInterface {
    List<Station> getAllStations();

    Optional<Station> getStationById(int id);

    Station addStation(Station station);
}
