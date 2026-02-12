package com.example.public_transportation.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.public_transportation.Models.StationLineKey;
import com.example.public_transportation.Models.Station_Line;

@Repository
public interface Station_LineRepository extends JpaRepository<Station_Line, StationLineKey> {

    // שימוש ב-id_lineId כדי להגיד ל-Spring: "כנס ל-id (המפתח) וחפש שם את lineId"
    List<Station_Line> findById_LineIdAndStationOrderGreaterThanEqual(Integer lineId, Integer stationOrder);

    List<Station_Line> findById_LineIdAndStationOrderGreaterThan(Integer lineId, Integer stationOrder);

    // מוצא את השורה הספציפית לפי המפתח המורכב
    Optional<Station_Line> findById_LineIdAndId_StationId(Integer lineId, Integer stationId);

    List<Station_Line> findById_LineIdAndStationOrderLessThan(int lineId, int stationOrder);
    
    // בונוס: שליפת כל התחנות של קו מסוים מסודרות לפי הסדר
    List<Station_Line> findById_LineIdOrderByStationOrderAsc(Integer lineId);

    Station_Line findByLineIdAndStationId(int lineId, int stationId);

}
