package com.thembelanimkhize.robotapocalypse.controllers;

import com.thembelanimkhize.robotapocalypse.entity.Robot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/robotData")
public class RobotController {
    private static final String apiUrl = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";

    private final WebClient webClient;

    public RobotController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<String>> getRobotData() {
        return webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .map(result -> ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(result))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/flying")
    public Mono<ResponseEntity<List<Robot>>> getFlyingRobots() {
        WebClient webClient = WebClient.create();

        Flux<Robot> robotFlux = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToFlux(Robot.class);

        Mono<List<Robot>> flyingRobotsMono = robotFlux
                .filter(robot -> "Flying".equals(robot.getCategory().name()))
                .collectList();

        return flyingRobotsMono.map(robots ->
                ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(robots)
        );
    }

    @GetMapping("/land")
    public Mono<ResponseEntity<List<Robot>>> getLandRobots() {
        WebClient webClient = WebClient.create();

        Flux<Robot> robotFlux = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToFlux(Robot.class)
                .filter(robot -> Robot.Category.Land.equals(robot.getCategory()));

        Mono<List<Robot>> landRobotsMono = robotFlux.collectList();

        return landRobotsMono.map(robots ->
                ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(robots)
        );
    }
}
