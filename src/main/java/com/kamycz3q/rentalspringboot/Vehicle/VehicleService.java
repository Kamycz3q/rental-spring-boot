package com.kamycz3q.rentalspringboot.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    //listowanie pojazdow
    public List<Vehicle> ListVehicles() {
        return vehicleRepository.findAll();
    }
    //dane o pojezdzie o podanym id
    public Optional<Vehicle> GetVehicleById(String id) {
        return vehicleRepository.findById(Integer.parseInt(id));
    }
    //dodwanie pojazdu
    public void AddVehicle(String make, String model, Integer year, String plate, String category) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setPlate(plate);
        vehicle.setCategory(category);
        vehicleRepository.save(vehicle);
    }
    //updateowanie pojazdu
    public void UpdateVehicle(String id, String make, String model, Integer year, String plate, String category) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(Integer.parseInt(id));
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setPlate(plate);
        vehicle.setCategory(category);
        vehicleRepository.save(vehicle);
    }

    //zmiana statusu wypozyczenia pojazdu
    public void ChangeVehicleState(String id, Boolean state) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(Integer.parseInt(id));
        vehicle.ifPresent(veh -> {
            veh.setRented(state);
            vehicleRepository.save(veh);
        });

    }
    //usuwanie pojazdu
    public void DeleteVehicle(String id) {
        vehicleRepository.deleteById(Integer.parseInt(id));
    }
}
