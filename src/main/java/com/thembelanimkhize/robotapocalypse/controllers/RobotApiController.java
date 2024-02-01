package com.thembelanimkhize.robotapocalypse.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thembelanimkhize.robotapocalypse.entity.Robot;
import com.thembelanimkhize.robotapocalypse.repository.RobotDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RobotApiController {

    private final RobotDataRepository robotDataRepository;

    public RobotApiController(RobotDataRepository robotDataRepository) {
        this.robotDataRepository = robotDataRepository;
    }

    @Value("${external.api.url}")
    private String apiUrl;

    @GetMapping("/robotData")
    public ResponseEntity<String> getRobotData() {
        WebClient webClient = WebClient.create();

        String result = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Save the data to the database
        List<Robot> robotData = parseJsonData(result);
        robotDataRepository.saveAll(robotData);

        System.out.println("----------------");
        System.out.println(result);
        System.out.println("-----------");


        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(result);
    }

    private List<Robot> parseJsonData(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeReference<List<Robot>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(jsonData, typeReference);
        } catch (JsonProcessingException e) {
            // Handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

