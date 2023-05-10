package com.kamycz3q.rentalspringboot.Rental;

import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
import com.kamycz3q.rentalspringboot.User.UserService;
import com.kamycz3q.rentalspringboot.Vehicle.Vehicle;
import com.kamycz3q.rentalspringboot.Vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final UserService userService;
    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public RentalService(UserService userService, RentalRepository rentalRepository, VehicleRepository vehicleRepository) {
        this.userService = userService;
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Rental> GetRental(String rentalId) {
        if (!rentalRepository.existsById(Integer.valueOf(rentalId))) {
            throw new ApiRequestException("Rental with id " + rentalId + " doesn't exist");
        }
        return rentalRepository.findById(Integer.valueOf(rentalId));


    }

    public Rental CreateRental(int ownerId, int vehId, long timestampStart, long timestampEnd) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehId);
        if (userService.GetUser(String.valueOf(ownerId)).isEmpty()) {
            throw new ApiRequestException("User with id " + ownerId + " not found in database!");
        }
        if (!vehicle.isPresent()) {
            throw new ApiRequestException("Vehicle with id " + vehId + " not found in database!");
        }
        Timestamp tStart = new Timestamp(timestampStart);
        Timestamp tEnd = new Timestamp(timestampEnd);
        if (tStart.getDate() - tEnd.getDate() < 0) {
            throw new ApiRequestException("Start date of the period is later than end date of the period");
        }
        vehicle.ifPresent(veh -> {
            if (veh.isRented()) {
                throw  new ApiRequestException("Vehicle with plate " + veh.getPlate() + " is rented");
            } else {
                veh.setRented(true);
                vehicleRepository.save(veh);
            }
        });
        Rental rental = new Rental();
        rental.setOwnerId(ownerId);
        rental.setVehicleId(vehId);
        rental.setPeriodStart(tStart);
        rental.setPeriodEnd(tEnd);
        rentalRepository.save(rental);

        return rental;
    }

    public List<Rental> ListRentals() {
        return rentalRepository.findAll();
    }

    public boolean DeleteRental(String rentalId) {
        if (!rentalRepository.existsById(Integer.valueOf(rentalId))) {
            throw new ApiRequestException("Rental with id " + " doesn't exists!");
        }
        Optional<Rental> rental = rentalRepository.findById(Integer.valueOf(rentalId));
        rental.ifPresent(rent -> {
            Optional<Vehicle> vehicle = vehicleRepository.findById(rent.getVehicleId());
            vehicle.ifPresent(veh -> {
                veh.setRented(false);
                vehicleRepository.save(veh);
            });
        });
        rentalRepository.deleteById(Integer.valueOf(rentalId));

        return true;
    }

    public Rental UpdateRental(String _rentalId, int ownerId, int vehId, long timestampStart, long timestampEnd) {
        int rentalId = Integer.valueOf(_rentalId);
        Timestamp tStart = new Timestamp(timestampStart);
        Timestamp tEnd = new Timestamp(timestampEnd);
        if (!rentalRepository.existsById(rentalId)) {
            throw new ApiRequestException("Rental with id " + " doesn't exists!");
        }
        Rental rental = new Rental();
        rental.setId(rentalId);
        rental.setOwnerId(ownerId);
        rental.setVehicleId(vehId);
        rental.setPeriodStart(tStart);
        rental.setPeriodEnd(tEnd);
        rentalRepository.saveAndFlush(rental);
        return rental;
    }

    public boolean ActivateRentalState(String _rentalId) {
        int rentalId = Integer.valueOf(_rentalId);
        if (!rentalRepository.existsById(rentalId)) {
            throw new ApiRequestException("Rental with id " + " doesn't exists!");
        }
        Rental rental = rentalRepository.getReferenceById(rentalId);
        rental.setState(true);
        rentalRepository.save(rental);
        return true;
    }

    public List<Rental> GetRentalsForUserId(String _userId) {
        int userId = Integer.valueOf(_userId);
        if (userService.GetUser(_userId).isEmpty()) {
            throw new ApiRequestException("User with id " + _userId + "doesn't exists!");
        }
        List<Rental> allRentals = ListRentals();
        List<Rental> userRentals = new ArrayList<>();
        for (Rental rental : allRentals) {
            if (rental.getOwnerId() == userId) {
                userRentals.add(rental);
            }
        }
        return userRentals;
    }
}
