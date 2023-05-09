package com.kamycz3q.rentalspringboot.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> ListVehicles() {
        return vehicleService.ListVehicles();
    }
    @GetMapping("/{id}")
    public Optional<Vehicle> GetVehicle(@PathVariable(name = "id") String id) {
        return vehicleService.GetVehicleById(id);
    }

    record CreateVehicleRequest(
            String make,
            String model,
            Integer year,
            String plate,
            String category
    ) { }
    @PostMapping
    public void CreateVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
        vehicleService.AddVehicle(
                createVehicleRequest.make(),
                createVehicleRequest.model(),
                createVehicleRequest.year(),
                createVehicleRequest.plate(),
                createVehicleRequest.category()
        );
    }
    @PatchMapping("/{id}")
    public void UpdateVehicle(@PathVariable(name = "id") String id,@RequestBody CreateVehicleRequest createVehicleRequest) {
        vehicleService.UpdateVehicle(
                id,
                createVehicleRequest.make(),
                createVehicleRequest.model(),
                createVehicleRequest.year(),
                createVehicleRequest.plate(),
                createVehicleRequest.category()
        );
    }
    @PatchMapping("/{id}/{state}")
    public void ChangeVehicleState(@PathVariable(name = "id") String id, @PathVariable(name = "state") String state) {
        boolean stateboolean;
        if (state == "0") {
            stateboolean = false;
        } else {
            stateboolean = true;
        }
        vehicleService.ChangeVehicleState(id, stateboolean);
    }

}
