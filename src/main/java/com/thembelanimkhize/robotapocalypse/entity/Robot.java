package com.thembelanimkhize.robotapocalypse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Robot {

    private String model;
    private String serialNumber;
    //private ZonedDateTime manufacturedDate;
    private String manufacturedDate;
    private Category category;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum Category {
        Flying, Land
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
