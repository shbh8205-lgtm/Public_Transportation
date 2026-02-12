package com.example.public_transportation.Controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.public_transportation.Models.Line;
import com.example.public_transportation.Services.LineServiceInterface;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/lines")
public class LineController {
    private final LineServiceInterface lineService;

    public LineController(LineServiceInterface lineService) {
        this.lineService = lineService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Line>> getAll() {
        return ResponseEntity.ok().body(lineService.getAllLines());
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Line> getById(@PathVariable Integer id) {
        return lineService.getLineById(id)
            .map(line -> ResponseEntity.ok().body(line))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Line> add(@RequestBody Line line) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lineService.addLine(line));
    }

}
