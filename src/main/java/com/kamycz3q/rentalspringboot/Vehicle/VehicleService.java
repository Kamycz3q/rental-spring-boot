package com.kamycz3q.rentalspringboot.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> ListVehicles() {
        return vehicleRepository.findAll();
    }
    public Optional<Vehicle> GetVehicleById(String id) {
        return vehicleRepository.findById(Integer.parseInt(id));
    }
    public void AddVehicle(String make, String model, Integer year, String plate, String category) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setPlate(plate);
        vehicle.setCategory(category);
        vehicleRepository.save(vehicle);
    }
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

    public void ChangeVehicleState(String id, Boolean state) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(Integer.parseInt(id));
        vehicle.ifPresent(veh -> {
            veh.setRented(state);
            vehicleRepository.save(veh);
        });

    }
}
