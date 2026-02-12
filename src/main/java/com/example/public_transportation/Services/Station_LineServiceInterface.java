package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import com.example.public_transportation.Models.StationLineKey;
import com.example.public_transportation.Models.Station_Line;

public interface Station_LineServiceInterface {
    List<Station_Line> getAllStation_Linees();

    Optional<Station_Line> getStation_LineById(StationLineKey id);

    Station_Line addStationToLine(String lineNumber, int stationNumber, int position);

    Station_Line removeStationToLine(int lineId, int stationId, int position);

}
