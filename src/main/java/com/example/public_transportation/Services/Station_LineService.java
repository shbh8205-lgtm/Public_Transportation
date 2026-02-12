package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.public_transportation.Models.Station;
import com.example.public_transportation.Models.Line;
import com.example.public_transportation.Models.StationLineKey;
import com.example.public_transportation.Models.Station_Line;
import com.example.public_transportation.Repositories.LineRepository;
import com.example.public_transportation.Repositories.StationRepository;
import com.example.public_transportation.Repositories.Station_LineRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class Station_LineService implements Station_LineServiceInterface {
    private final Station_LineRepository station_LineRepository;
    private final StationRepository stationRepository;
    private final LineRepository lineRepository;

    public Station_LineService(Station_LineRepository station_LineRepository, StationRepository stationRepository,
            LineRepository lineRepository) {
        this.station_LineRepository = station_LineRepository;
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
    }

    @Override
    @Transactional
    public Station_Line addStationToLine(String lineNumber, int stationNumber, int order) {
        // 1. שליפת אובייקטים
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line not found"));
        Station station = stationRepository.findByNumber(stationNumber)
                .orElseThrow(() -> new EntityNotFoundException("Station not found"));

        // 2. הזזה קדימה: כל מה שבמיקום המיועד ומעלה - זז מקום אחד קדימה (+1)
        List<Station_Line> subsequentStations = station_LineRepository
                .findById_LineIdAndStationOrderGreaterThanEqual(line.getId(), order);

        for (Station_Line sl : subsequentStations) {
            sl.setStationOrder(sl.getStationOrder() + 1);
        }
        station_LineRepository.saveAll(subsequentStations);

        // 3. יצירת ושמירת התחנה החדשה
        StationLineKey key = new StationLineKey(station.getId(), line.getId());
        Station_Line sl = Station_Line.builder()
                .id(key)
                .line(line)
                .station(station)
                .stationOrder(order)
                .build();

        return station_LineRepository.save(sl);
    }

    @Override
    @Transactional
    public Station_Line removeStationToLine(int lineId, int stationId, int position) {
        StationLineKey key = new StationLineKey(stationId, lineId);

        // 1. מציאת התחנה לפני המחיקה כדי שנוכל להחזיר אותה
        Station_Line slToDelete = station_LineRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException("Station_Line connection not found"));

        // 2. מחיקת התחנה
        station_LineRepository.delete(slToDelete);

        // 3. הזזה אחורה: כל מה שהיה מעל המיקום שנמחק - זז מקום אחד אחורה (-1)
        List<Station_Line> subsequentStations = station_LineRepository
                .findById_LineIdAndStationOrderGreaterThan(lineId, position);

        for (Station_Line sl : subsequentStations) {
            sl.setStationOrder(sl.getStationOrder() - 1);
        }
        station_LineRepository.saveAll(subsequentStations);

        return slToDelete;
    }

    @Override
    public List<Station_Line> getAllStation_Linees() {
        return station_LineRepository.findAll();
    }

    @Override
    public Optional<Station_Line> getStation_LineById(StationLineKey id) {
        return station_LineRepository.findById(id);
    }
}