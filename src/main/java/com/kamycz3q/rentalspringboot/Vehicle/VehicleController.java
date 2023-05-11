package com.kamycz3q.rentalspringboot.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> ListVehicles() {
        return vehicleService.ListVehicles();
    }
    @GetMapping("/{id}")
    public Vehicle GetVehicle(@PathVariable(name = "id") String id) {
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
    public Vehicle CreateVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
         return vehicleService.AddVehicle(
                createVehicleRequest.make(),
                createVehicleRequest.model(),
                createVehicleRequest.year(),
                createVehicleRequest.plate(),
                createVehicleRequest.category()
        );
    }
    @PatchMapping("/{id}")
    public Vehicle UpdateVehicle(@PathVariable(name = "id") String id,@RequestBody CreateVehicleRequest createVehicleRequest) {
        return vehicleService.UpdateVehicle(
                id,
                createVehicleRequest.make(),
                createVehicleRequest.model(),
                createVehicleRequest.year(),
                createVehicleRequest.plate(),
                createVehicleRequest.category()
        );
    }
    //zmiana statusu pojazdu
    @PatchMapping("/{id}/{state}")
    public boolean ChangeVehicleState(@PathVariable(name = "id") String id, @PathVariable(name = "state") String state) {
        boolean stateboolean = true;
        if (state.equals("0")) {
            stateboolean = false;
        }
        return vehicleService.ChangeVehicleState(id, stateboolean);
    }

    //usuwanie pojazdu
    @DeleteMapping("/{id}")
    public boolean DeleteVehicle(@PathVariable (name = "id") String id) {
        return vehicleService.DeleteVehicle(id);
    }

}
