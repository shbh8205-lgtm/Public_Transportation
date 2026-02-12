package com.example.public_transportation.Repositories;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.public_transportation.Models.Line;
import com.example.public_transportation.Models.Station;
import com.example.public_transportation.Models.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {
    @Query("SELECT t FROM Travel t WHERE t.line = ?1 AND t.departureTime > ?2 ORDER BY t.departureTime ASC")
    List<Travel> findNextDepartureByLine(Line line, LocalTime currentTime);

    @Query("SELECT sl.station FROM Station_Line sl WHERE sl.line.id = :lineId ORDER BY sl.stationOrder ASC")
    List<Station> findBusLocationsByLineAndDepartureTime(String lineNumber, LocalTime currentTime);

    // מחליף את הלולאה ב-getTripsByHour
    List<Travel> findByDepartureTime(LocalTime departureTime);

    // מחליף את הלולאה ב-getLastTripOfTheDay
    // מוצא את הנסיעה האחרונה לפי זמן היציאה (הראשון בסדר יורד)
    Optional<Travel> findFirstByOrderByDepartureTimeDesc();

    // לשימוש עתידי: מציאת נסיעות קרובות לקו מסוים
    List<Travel> findByLineAndDepartureTimeAfterOrderByDepartureTimeAsc(Line line, LocalTime now);
}