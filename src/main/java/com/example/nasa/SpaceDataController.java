
package com.example.nasa;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/space")
public class SpaceDataController {

    private final SpaceDataService spaceDataService;

    public SpaceDataController(SpaceDataService spaceDataService) {
        this.spaceDataService = spaceDataService;
    }

    @GetMapping(value = "/fact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getApodWithFact() {
        ApodResponse apod = spaceDataService.getApod();
        Map<String, String> response = spaceDataService.createApodWithFactResponse();
        return ResponseEntity.ok(response);
    }
}
