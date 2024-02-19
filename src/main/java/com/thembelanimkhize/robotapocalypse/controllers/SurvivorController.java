package com.thembelanimkhize.robotapocalypse.controllers;

import com.thembelanimkhize.robotapocalypse.entity.Location;
import com.thembelanimkhize.robotapocalypse.entity.Survivor;
import com.thembelanimkhize.robotapocalypse.repository.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/survivors")
public class SurvivorController {
    private final SurvivorRepository survivorRepository;

    public SurvivorController(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    @GetMapping()
    private ResponseEntity<Iterable<Survivor>> findAll() {
        return ResponseEntity.ok(survivorRepository.findAll());
    }
    @GetMapping("/{id}")
    private ResponseEntity<Survivor> findById(@PathVariable int id) {
        Optional<Survivor> survivor = survivorRepository.findById(id);
        return survivor.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSurvivorsLocation(
            @PathVariable int id,
            @RequestBody Location survivorUpdateLocation) {

        Optional<Survivor> optionalSurvivor = survivorRepository.findById(id);

        if (optionalSurvivor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Survivor survivor = optionalSurvivor.get();

        Location currentLocation = survivor.getLastLocation();
        currentLocation.setLatitude(survivorUpdateLocation.getLatitude());
        currentLocation.setLongitude(survivorUpdateLocation.getLongitude());

        survivorRepository.save(survivor);

        return ResponseEntity.noContent().build();
    }
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable int id) {
//        survivorRepository.deleteById(id);
//    }


    @PostMapping
    private ResponseEntity<Void> createSurvivor(@RequestBody Survivor newSurvivorRequest, UriComponentsBuilder ucb) {
        Survivor savedSurvivor = survivorRepository.save(newSurvivorRequest);
        //System.out.println(savedSurvivor.id());
        URI locationOfNewSurvivor = ucb
                .path("survivors/{id}")
                //Note that survivors.id is used as the identifier,
                // which matches the GET endpoint's specification of survivors/<survivors.id>.
                .buildAndExpand(savedSurvivor.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewSurvivor).build();
    }

    @PutMapping("/{id}/reportContamination")
    public ResponseEntity<?> reportContamination(@PathVariable int id) {
        Optional<Survivor> optionalSurvivor = survivorRepository.findById(id);

        if (optionalSurvivor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Survivor survivor = optionalSurvivor.get();
        survivor.reportContamination();
        survivorRepository.save(survivor);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/infectedSurvivors")
    public List<String> getActiveUserNames() {
        List<Survivor> infectedSurvivors = survivorRepository.findByInfectedTrue();
        return infectedSurvivors.stream()
                .map(infected -> infected.getName() + " " + infected.getGender())
                .toList();
    }

    @GetMapping("/nonInfectedSurvivors")
    public List<String> getInactiveUserNames() {
        List<Survivor> nonInfectedSurvivors = survivorRepository.findByInfectedFalse();
        return nonInfectedSurvivors.stream()
                .map(nonInfected -> nonInfected.getName() + " " + nonInfected.getGender())
                .toList();
    }
    @GetMapping("/infectedSurvivorsPercentage")
    public double getInfectedSurvivorsPercentage() {
        long totalSurvivors = survivorRepository.count();
        //long infectedSurvivors = survivorRepository.countInfectedSurvivors();
        long infectedSurvivors = survivorRepository.countByInfectedTrue();

        if (totalSurvivors == 0) {
            return 0.0; // Handle division by zero
        }

        return (double) infectedSurvivors / totalSurvivors * 100;
    }
    @GetMapping("/nonInfectedSurvivorsPercentage")
    public double getNonInfectedSurvivorsPercentage() {
        long totalSurvivors = survivorRepository.count();
        //long nonInfectedSurvivors = survivorRepository.countNonInfectedSurvivors();
        long nonInfectedSurvivors = survivorRepository.countByInfectedFalse();

        if (totalSurvivors == 0) {
            return 0.0; // Handle division by zero
        }

        return (double) nonInfectedSurvivors / totalSurvivors * 100;
    }

}
