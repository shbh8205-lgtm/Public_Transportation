package com.example.public_transportation.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.public_transportation.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
        Optional<Station> findByNumber(int number);

}
