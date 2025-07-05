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
        String fact = spaceDataService.getRandomSpaceFact();

        Map<String, String> response = new HashMap<>();
        response.put("title", apod.getTitle());
        response.put("explanation", apod.getExplanation());
        response.put("image_url", apod.getUrl()); // или apod.getHdurl() для картинки в высоком разрешении
        response.put("space_fact", fact);

        return ResponseEntity.ok(response);
    }
}