package com.example.public_transportation.Models;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("departure_time")
    private LocalTime departureTime;

    @ManyToOne
    private Bus bus;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Line line;

    public LocalTime calculateArrivalTime(Integer stopOrder) {
        return departureTime.plusMinutes(stopOrder - 1);
    }
}
