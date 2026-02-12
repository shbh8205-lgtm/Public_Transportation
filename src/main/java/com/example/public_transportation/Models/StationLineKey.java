package com.example.public_transportation.Models;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // אנוטציה קריטית - אומרת שזה חלק מישות אחרת
public class StationLineKey implements Serializable {

    private Integer stationId;
    private Integer lineId;

    // חובה ב-JPA להגדיר equals ו-hashCode עבור מפתחות מורכבים
    // ה-@Data של Lombok עושה את זה, אבל הנה המימוש למקרה שצריך:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationLineKey that = (StationLineKey) o;
        return stationId == that.stationId && lineId == that.lineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationId, lineId);
    }
}