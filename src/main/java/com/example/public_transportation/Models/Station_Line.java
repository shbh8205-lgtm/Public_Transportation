package com.example.public_transportation.Models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Station_Line {

    @EmbeddedId
    private StationLineKey id;
    @ManyToOne
    @MapsId("stationId")
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne
    @MapsId("lineId")
    @JoinColumn(name = "line_id")
    private Line line;

    private Integer stationOrder;

    public Station_Line(Station station, Line line, int order) {
        this.station = station;
        this.line = line;
        this.stationOrder = order;

        this.id = new StationLineKey(station.getId(), line.getId());
    }
}