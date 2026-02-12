package com.example.public_transportation.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.public_transportation.Models.Station_Line;
import com.example.public_transportation.Services.Station_LineServiceInterface;

@RestController
@RequestMapping("/stationline")
public class Station_LineController {

    private final Station_LineServiceInterface slService;

    public Station_LineController(Station_LineServiceInterface slService) {
        this.slService = slService;
    }

    @PostMapping("/addstationtoline")
    public ResponseEntity<Station_Line> add(@RequestParam String linenumber, @RequestParam Integer stationnumber,
            @RequestParam int position) {
        return ResponseEntity.ok().body(slService.addStationToLine(linenumber, stationnumber, position));
    }

    @PostMapping("/deletestationtoline")
    public ResponseEntity<Station_Line> delete(@RequestParam Integer lineid, @RequestParam Integer stationid,
            @RequestParam int position) {
        return ResponseEntity.ok().body(slService.removeStationToLine(lineid, stationid, position));
    }
}
