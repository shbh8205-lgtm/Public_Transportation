package com.example.public_transportation.Services;
import java.util.List;
import java.util.Optional;

import com.example.public_transportation.Models.Travel;

public interface TravelServiceInterface {
    List<Travel> getAllTravels();

    Optional<Travel> getTravelById(int id);

    Travel addTravel(Travel travel);
}
