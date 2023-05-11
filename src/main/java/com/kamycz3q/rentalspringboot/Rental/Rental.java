package com.kamycz3q.rentalspringboot.Rental;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @SequenceGenerator(
            name = "rental_seq",
            sequenceName = "rental_seq",
            allocationSize = 10
    )
    @GeneratedValue(
            generator = "rental_seq",
            strategy = GenerationType.SEQUENCE
    )
    private int id;
    private int ownerId;
    private int vehicleId;

    private int workerId;
    private Timestamp periodStart, periodEnd;
    private boolean state;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getOwnerId() {
        return ownerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    public Timestamp getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Timestamp periodStart) {
        this.periodStart = periodStart;
    }

    public Timestamp getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Timestamp periodEnd) {
        this.periodEnd = periodEnd;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", state=" + state +
                '}';
    }
}
