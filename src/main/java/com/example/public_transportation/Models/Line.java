package com.example.public_transportation.Models;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String source;
    private String destination;

    @OneToMany(mappedBy = "line")
    @OrderBy("stationOrder ASC")
    private List<Station_Line> stations;
}
