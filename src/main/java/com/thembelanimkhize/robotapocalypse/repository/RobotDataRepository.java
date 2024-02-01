package com.thembelanimkhize.robotapocalypse.repository;

import com.thembelanimkhize.robotapocalypse.entity.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobotDataRepository extends JpaRepository<Robot, String> {
}
