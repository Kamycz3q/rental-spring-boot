package com.kamycz3q.rentalspringboot.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rent")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    record CreateRentalRequest(
            int ownerId,
            int vehicleId,
            int workerId,
            long timestampStart,
            long timestampEnd
    ) {

    }
    @PostMapping
    public Rental CreateRental(@RequestBody CreateRentalRequest createRentalRequest) {
        return rentalService.CreateRental(
                createRentalRequest.ownerId(),
                createRentalRequest.vehicleId(),
                createRentalRequest.workerId(),
                createRentalRequest.timestampStart(),
                createRentalRequest.timestampEnd()
        );
    }

    @GetMapping
    public List<Rental> ListRentals() {
        return rentalService.ListRentals();
    }

    @DeleteMapping("/{id}")
    public boolean DeleteRental(@PathVariable(name = "id") String rentalId) {
        return rentalService.DeleteRental(rentalId);
    }

    record UpdateRentalRequest(
            int ownerId,
            int vehicleId,
            long timestampStart,
            long timestampEnd
    ) {

    }
    @PatchMapping("/{id}")
    public Rental UpdateRental(@PathVariable(name = "id") String rentalId, @RequestBody UpdateRentalRequest updateRentalRequest) {
        return rentalService.UpdateRental(rentalId,
                updateRentalRequest.ownerId(),
                updateRentalRequest.vehicleId(),
                updateRentalRequest.timestampStart(),
                updateRentalRequest.timestampEnd()
        );
    }

    @PatchMapping("/activate/{id}")
    public boolean ActivateRental(@PathVariable(name = "id") String rentalId) {
        return rentalService.ActivateRentalState(rentalId);
    }

    @GetMapping("/{id}")
    public Rental GetRental(@PathVariable(name = "id") String rentalId) {
        return rentalService.GetRental(rentalId);
    }
}
