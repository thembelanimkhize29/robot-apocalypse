package com.thembelanimkhize.robotapocalypse.repository;

import com.thembelanimkhize.robotapocalypse.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurvivorRepository extends JpaRepository<Survivor, Integer> {

//    @Query("SELECT COUNT(u) FROM Survivor u WHERE u.infected = true")
//    //@Query("SELECT COUNT(Survivor) FROM Survivor WHERE infected = true")
//    long countInfectedSurvivors();
      long countByInfectedTrue();

//    @Query("SELECT COUNT(u) FROM Survivor u WHERE u.infected = false")
//    //@Query("SELECT COUNT(Survivor) FROM Survivor WHERE infected = false")
//    long countNonInfectedSurvivors();
      long countByInfectedFalse();

    List<Survivor> findByInfectedTrue();
    List<Survivor> findByInfectedFalse();
}
