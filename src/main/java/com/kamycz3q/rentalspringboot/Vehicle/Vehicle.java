package com.kamycz3q.rentalspringboot.Vehicle;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "cars")
public class Vehicle {
    @Id
    @SequenceGenerator(
            name = "vehicles_seq",
            sequenceName = "vehicles_seq",
            allocationSize = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicles_seq"
    )
    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private String plate;
    private String category;
    private boolean isRented;

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
