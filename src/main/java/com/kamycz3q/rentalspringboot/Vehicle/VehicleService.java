package com.kamycz3q.rentalspringboot.Vehicle;

import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
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
        if (!vehicleRepository.existsById(Integer.valueOf(id))) {
            throw new ApiRequestException("Vehicle with id " + id + " doesn't exist!");
        }
        return vehicleRepository.findById(Integer.valueOf(id));
    }
    //dodwanie pojazdu
    public Vehicle AddVehicle(String make, String model, Integer year, String plate, String category) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setPlate(plate);
        vehicle.setCategory(category);
        vehicleRepository.save(vehicle);
        return vehicle;
    }
    //updateowanie pojazdu
    public Vehicle UpdateVehicle(String id, String make, String model, Integer year, String plate, String category) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(Integer.parseInt(id));
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setPlate(plate);
        vehicle.setCategory(category);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    //zmiana statusu wypozyczenia pojazdu
    public boolean ChangeVehicleState(String id, Boolean state) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(Integer.valueOf(id));
        if (!vehicleRepository.existsById(Integer.valueOf(id))) {
            throw new ApiRequestException("Vehicle with id " + id + " doesn't exists!");
        }
        vehicle.ifPresent(veh -> {
            veh.setRented(state);
            vehicleRepository.save(veh);
        });
        return true;

    }
    //usuwanie pojazdu
    public boolean DeleteVehicle(String id) {
        if (!vehicleRepository.existsById(Integer.valueOf(id))) {
            throw new ApiRequestException("Vehicle with id " + id + " doesn't exist");
        }
        vehicleRepository.deleteById(Integer.valueOf(id));
        return true;
    }
}
