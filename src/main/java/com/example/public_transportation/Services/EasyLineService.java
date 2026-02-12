package com.example.public_transportation.Services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.public_transportation.Models.Station;
import com.example.public_transportation.Models.Station_Line;
import com.example.public_transportation.Models.Travel;
import com.example.public_transportation.Models.Line;
import com.example.public_transportation.Repositories.LineRepository;
import com.example.public_transportation.Repositories.TravelRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EasyLineService {
    private final LineRepository lineRepository;
    private final TravelRepository travelRepository;

    public EasyLineService(LineRepository lineRepository, TravelRepository travelRepository) {
        this.lineRepository = lineRepository;
        this.travelRepository = travelRepository;
    }

    // חיפוש לפי תחנה
    public LocalTime getArrivalTimeByStation(int stationNumber, String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new IllegalArgumentException("קו לא קיים"));

        List<Travel> upcomingTravels = travelRepository.findByLineAndDepartureTimeAfterOrderByDepartureTimeAsc(line,
                LocalTime.now());

        if (upcomingTravels.isEmpty()) {
            throw new IllegalArgumentException("אין נסיעות זמינות לקו זה");
        }

        return upcomingTravels.get(0).calculateArrivalTime(stationNumber);
    }

    // חיפוש לפי קו - מיקום האוטובוסים
    public List<Station> getBusLocationsByLine(String lineNumber) {
        return travelRepository.findBusLocationsByLineAndDepartureTime(lineNumber, LocalTime.now());
    }

    // חיפוש לפי קו - שמיעת כל תחנות הקו
    public List<Station_Line> getStationsByLine(String lineNumber) {
        return lineRepository.findByNumber(lineNumber)
                .<List<Station_Line>>map(Line::getStations)
                .orElseGet(ArrayList::new);
    }

    // לוחות זמנים
    public List<Travel> getAllTrips() {
        List<Travel> allTrips = travelRepository.findAll();
        return allTrips;
    }

    // לוחות זמנים - חיפוש לפי שעה (בלי לולאת for)
    public List<Travel> getTripsByHour(LocalTime hour) {
        return travelRepository.findByDepartureTime(hour);
    }

    // נסיעה אחרונה ביום (בלי לולאת for)
    public Travel getLastTripOfTheDay() {
        return travelRepository.findFirstByOrderByDepartureTimeDesc()
                .orElse(null);
    }
}
