package com.example.nasa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SpaceDataService {

    private final RestTemplate restTemplate;

    @Value("${nasa.api.key}") // В application.properties
    private String nasaApiKey;

    public SpaceDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApodResponse getApod() {
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + nasaApiKey;
        return restTemplate.getForObject(url, ApodResponse.class);
    }

    public String getRandomSpaceFact() {
        List<String> facts = List.of(
                "Солнце составляет 99,86% массы Солнечной системы.",
                "На Венере день длиннее года.",
                "Самая высокая гора в Солнечной системе находится на Марсе (гора Олимп).",
                "Нейтронные звезды могут вращаться со скоростью до 600 оборотов в секунду.",
                "Во Вселенной больше звезд, чем песчинок на всех пляжах Земли."
        );
        Random random = new Random();
        int index = random.nextInt(facts.size());
        return facts.get(index);
    }

    public Map<String, String> createApodWithFactResponse() {
        ApodResponse apod = getApod();
        String fact = getRandomSpaceFact();

        Map<String, String> response = new HashMap<>();
        response.put("title", apod.getTitle());
        response.put("explanation", apod.getExplanation());
        response.put("image_url", apod.getUrl()); // или apod.getHdurl() для картинки в высоком разрешении
        response.put("space_fact", fact);

        return response;
    }

}