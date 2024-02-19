package com.thembelanimkhize.robotapocalypse.Testcontrollers;

import com.thembelanimkhize.robotapocalypse.entity.InventoryOfResources;
import com.thembelanimkhize.robotapocalypse.entity.Location;
import com.thembelanimkhize.robotapocalypse.entity.Survivor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import java.net.URI;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSurvivor {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllSurvivorsWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/survivors", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void shouldReturnASurvivorWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/survivors/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void shouldCreateANewSurvivor() {
          Survivor newSurvivor = new Survivor(0,"Alice", 30, "Female",new Location(37.7833, -122.4167), new InventoryOfResources(0,10, 20, 5, 0), Arrays.asList(1, 1, 1),true);
//        Survivor newSurvivor = new Survivor(0,"Bbb",30,"Male", 250.00,25.22);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/survivors", newSurvivor, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewSurvivor = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewSurvivor, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    @DirtiesContext
    void shouldUpdateAnExistingSurvivorLocation() {
        Location updatedLocation = new Location(34.7749, -124.4194);
        HttpEntity<Location> request = new HttpEntity<>(updatedLocation);
        ResponseEntity<Void> response = restTemplate
                .exchange("/survivors/1", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    void testReportContamination() {
        // Create a Survivor instance
        Survivor survivor = new Survivor();

        // Initially, the survivor should not be infected
        assertFalse(survivor.isInfected());

        // Report contamination three times
        survivor.reportContamination();
        survivor.reportContamination();
        survivor.reportContamination();

        // After three contamination reports, the survivor should be infected
        assertTrue(survivor.isInfected());
    }
}
