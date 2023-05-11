package com.kamycz3q.rentalspringboot.Rental;

import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
import com.kamycz3q.rentalspringboot.Logging.LogService;
import com.kamycz3q.rentalspringboot.User.UserService;
import com.kamycz3q.rentalspringboot.Vehicle.Vehicle;
import com.kamycz3q.rentalspringboot.Vehicle.VehicleRepository;
import com.kamycz3q.rentalspringboot.Worker.WorkerService;
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
    private final WorkerService workerService;
    private final LogService logService;

    @Autowired
    public RentalService(UserService userService, RentalRepository rentalRepository, VehicleRepository vehicleRepository, WorkerService workerService, LogService logService) {
        this.userService = userService;
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.workerService = workerService;
        this.logService = logService;
    }

    public Rental GetRental(String rentalId) {
        Optional<Rental> optionalRental = rentalRepository.findById(Integer.valueOf(rentalId));
        if (!optionalRental.isPresent()) {
            throw new ApiRequestException("Rental with id " + rentalId + " doesn't exist");
        }
        return optionalRental.get();


    }

    public Rental CreateRental(int ownerId, int vehId, int workerId, long timestampStart, long timestampEnd) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehId);
        if (userService.GetUser(String.valueOf(ownerId)) == null) {
            throw new ApiRequestException("User with id " + ownerId + " not found in database!");
        }
        if (!optionalVehicle.isPresent()) {
            throw new ApiRequestException("Vehicle with id " + vehId + " not found in database!");
        }
        if (workerService.GetWorkerById(String.valueOf(workerId)) == null) {
            throw new ApiRequestException("Worker with id " + ownerId + " not found in database!");
        }
        Timestamp tStart = new Timestamp(timestampStart);
        Timestamp tEnd = new Timestamp(timestampEnd);
        if (tStart.getDate() - tEnd.getDate() < 0) {
            throw new ApiRequestException("Start date of the period is later than end date of the period");
        }
        Vehicle vehicle = optionalVehicle.get();
        if (vehicle.isRented()) {
            throw  new ApiRequestException("Vehicle with plate " + vehicle.getPlate() + " is rented");
        } else {
            vehicle.setRented(true);
            vehicleRepository.save(vehicle);
        }
        Rental rental = new Rental();
        rental.setOwnerId(ownerId);
        rental.setVehicleId(vehId);
        rental.setPeriodStart(tStart);
        rental.setPeriodEnd(tEnd);
        rental.setWorkerId(workerId);
        rentalRepository.save(rental);
        logService.AddLog(String.valueOf(workerId), "Created rent for user: " + ownerId + " for vehicle with id " + vehId);

        return rental;
    }

    public List<Rental> ListRentals() {
        return rentalRepository.findAll();
    }

    public boolean DeleteRental(String _rentalId) {
        int rentalId = Integer.valueOf(_rentalId);
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        if (!optionalRental.isPresent()) {
            throw new ApiRequestException("Rental with id " + _rentalId + " doesn't exists!");
        }


        Optional<Vehicle> vehicle = vehicleRepository.findById(optionalRental.get().getVehicleId());
        if (vehicle.isPresent()) {
            Vehicle veh = vehicle.get();
            veh.setRented(false);
            vehicleRepository.save(veh);
        }

        rentalRepository.deleteById(rentalId);

        return true;
    }

    public Rental UpdateRental(String _rentalId, int ownerId, int vehId, long timestampStart, long timestampEnd) {
        int rentalId = Integer.valueOf(_rentalId);
        Timestamp tStart = new Timestamp(timestampStart);
        Timestamp tEnd = new Timestamp(timestampEnd);
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        if (!optionalRental.isPresent()) {
            throw new ApiRequestException("Rental with id " + _rentalId + " doesn't exists!");
        }
        Rental rental = optionalRental.get();
        rental.setOwnerId(ownerId);
        rental.setVehicleId(vehId);
        rental.setPeriodStart(tStart);
        rental.setPeriodEnd(tEnd);
        rentalRepository.saveAndFlush(rental);
        return rental;
    }

    public boolean ActivateRentalState(String _rentalId) {
        int rentalId = Integer.valueOf(_rentalId);
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        if (!optionalRental.isPresent()) {
            throw new ApiRequestException("Rental with id " + _rentalId + " doesn't exists!");
        }

        Rental rental = optionalRental.get();
        rental.setState(true);
        rentalRepository.save(rental);
        return true;
    }

    public List<Rental> GetRentalsForUserId(String _userId) {
        int userId = Integer.valueOf(_userId);
        if (userService.GetUser(_userId) == null) {
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
