package com.thembelanimkhize.robotapocalypse.entity;

import jakarta.persistence.*;

@Entity
public class InventoryOfResources {
    //Quantity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int water;
    private int food;
    private int medication;
    private int ammunition;

    public InventoryOfResources() {
    }

    public InventoryOfResources(int id, int water, int food, int medication, int ammunition) {
        this.id = id;
        this.water = water;
        this.food = food;
        this.medication = medication;
        this.ammunition = ammunition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getMedication() {
        return medication;
    }

    public void setMedication(int medication) {
        this.medication = medication;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    @Override
    public String toString() {
        return "InventoryOfResources{" +
                "id=" + id +
                ", water=" + water +
                ", food=" + food +
                ", medication=" + medication +
                ", ammunition=" + ammunition +
                '}';
    }
}
