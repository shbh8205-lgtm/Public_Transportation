package com.example.public_transportation.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.public_transportation.Models.Line;
import com.example.public_transportation.Repositories.LineRepository;

@Service
public class LineService implements LineServiceInterface {

    private final LineRepository lineRepository;

    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @Override
    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }

    @Override
    public Optional<Line> getLineById(int id) {
        return lineRepository.findById(id);
    }

    @Override
    public Line addLine(Line line) {
        return lineRepository.save(line);
    }
}
