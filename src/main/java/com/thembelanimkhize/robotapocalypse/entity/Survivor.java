package com.thembelanimkhize.robotapocalypse.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Survivor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String gender;
    private boolean infected;
    @Embedded
    private Location lastLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryOfResources inventory;

    @ElementCollection
    @CollectionTable(name = "survivor_contamination_reports")
    private List<Integer> contaminationReports = new ArrayList<>();

    //The default constructor exists only for the sake of JPA.
    public Survivor() {
    }

    public Survivor(int id, String name, int age, String gender, Location lastLocation, InventoryOfResources inventory, List<Integer> contaminationReports, boolean infected) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.infected = infected;
        this.lastLocation = lastLocation;
        this.inventory = inventory;
        this.contaminationReports = contaminationReports;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public InventoryOfResources getInventory() {
        return inventory;
    }

    public void setInventory(InventoryOfResources inventory) {
        this.inventory = inventory;
    }

    public List<Integer> getContaminationReports() {
        return contaminationReports;
    }

    public void setContaminationReports(List<Integer> contaminationReports) {
        this.contaminationReports = contaminationReports;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public void reportContamination() {
        contaminationReports.add(1);
        if (contaminationReports.size() >= 3) {
            setInfected(true);
        }
    }

}
