package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;

import com.example.public_transportation.Models.Line;

public interface LineServiceInterface {
    List<Line> getAllLines();

    Optional<Line> getLineById(int id);

    Line addLine(Line Line);
}
