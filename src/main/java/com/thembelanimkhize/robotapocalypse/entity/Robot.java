package com.thembelanimkhize.robotapocalypse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class Robot {
    @Id
    @Column(unique = true)
    private String model;
    private String serialNumber;
    //private ZonedDateTime manufacturedDate;
    private String manufacturedDate;
    private Category category;
    public enum Category {
        FLYING, LAND
    }

    public Robot() {
    }

    public Robot(String model, String serialNumber, String manufacturedDate, Category category) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.manufacturedDate = manufacturedDate;
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(String manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturedDate=" + manufacturedDate +
                ", category=" + category +
                '}';
    }
}
