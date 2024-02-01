package com.thembelanimkhize.robotapocalypse.repository;

import com.thembelanimkhize.robotapocalypse.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurvivorRepository extends JpaRepository<Survivor, Integer> {

    @Query("SELECT COUNT(u) FROM Survivor u WHERE u.infected = true")
    long countInfectedSurvivors();

    @Query("SELECT COUNT(u) FROM Survivor u WHERE u.infected = false")
    long countNonInfectedSurvivors();
    List<Survivor> findByInfectedTrue();
    List<Survivor> findByInfectedFalse();
}
