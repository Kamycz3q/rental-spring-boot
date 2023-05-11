package com.kamycz3q.rentalspringboot.Worker;

import jakarta.persistence.*;


@Entity
@Table(name = "workers")
public class Worker {
    @Id
    @SequenceGenerator(
            name = "workers_seq",
            sequenceName = "workers_seq",
            allocationSize = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workers_seq"
    )
    private int id;
    private String name, surname;
    private String email;



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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
