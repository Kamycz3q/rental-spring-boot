package com.kamycz3q.rentalspringboot.Rental;
import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
import com.kamycz3q.rentalspringboot.Logging.LogService;
import com.kamycz3q.rentalspringboot.User.User;
import com.kamycz3q.rentalspringboot.User.UserRepository;
import com.kamycz3q.rentalspringboot.User.UserService;
import com.kamycz3q.rentalspringboot.Vehicle.Vehicle;
import com.kamycz3q.rentalspringboot.Vehicle.VehicleRepository;
import com.kamycz3q.rentalspringboot.Vehicle.VehicleService;
import com.kamycz3q.rentalspringboot.Worker.Worker;
import com.kamycz3q.rentalspringboot.Worker.WorkerRepository;
import com.kamycz3q.rentalspringboot.Worker.WorkerService;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RentalServiceTest {
    @Mock private WorkerRepository workerRepository;
    @Mock private UserRepository userRepository;
    @Mock private LogService logService;
    @Mock private RentalRepository rentalRepository;
    @Mock private VehicleRepository vehicleRepository;
    @Mock private WorkerService workerService;
    @Mock
    private  VehicleService vehicleService;
    @Mock
    private UserService userService;
    @InjectMocks
    private RentalService rentalService;


    @Before("")
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void testGetRental() {
        Rental rental = new Rental();
        rental.setId(1);
        rental.setWorkerId(1);
        rental.setState(true);
        rental.setPeriodStart(new Timestamp(1683703611000L));
        rental.setPeriodEnd(new Timestamp(1715326005000L));

        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));


        Rental res = rentalService.GetRental("1");
        System.out.println(res);
        assertNotNull(res);
    }

    @Test
    final void testListRentals() {

        Rental rental = new Rental();
        rental.setId(1);
        rental.setWorkerId(1);
        rental.setState(true);
        rental.setPeriodStart(new Timestamp(1683703611000L));
        rental.setPeriodEnd(new Timestamp(1715326005000L));
        rentalRepository.save(rental);

        when(rentalRepository.findAll()).thenReturn(List.of(rental));


        List<Rental> res = rentalService.ListRentals();
        assertNotNull(res);
    }


    @Test
    final void testDeleteRental() {
        Rental rental = new Rental();
        rental.setId(1);
        rental.setWorkerId(1);
        rental.setState(true);
        rental.setPeriodStart(new Timestamp(1683703611000L));
        rental.setPeriodEnd(new Timestamp(1715326005000L));
        rentalRepository.save(rental);

        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        boolean res = rentalService.DeleteRental("1");
        assertEquals(true, res);
    }

    @Test
    final void testUpdateRental() {
        Rental rental = new Rental();
        rental.setId(1);
        rental.setWorkerId(1);
        rental.setState(true);
        rental.setPeriodStart(new Timestamp(1683703611000L));
        rental.setPeriodEnd(new Timestamp(1715326005000L));

        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        Rental res = rentalService.UpdateRental(String.valueOf(rental.getId()), rental.getOwnerId(), rental.getVehicleId(), rental.getPeriodStart().getTime(), rental.getPeriodEnd().getTime());
        assertNotNull(res);
    }


    @Test
    final void testActivateRental() {
        Rental rental = new Rental();
        rental.setId(1);
        rental.setWorkerId(1);

        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        boolean res = rentalService.ActivateRentalState("1");
        assertEquals(true, res);
    }

    @Test
    final void testAddRental() {
        User user = new User();
        user.setId(1);
        user.setName("as");
        user.setSurname("sa");
        user.setEmail("@");
        Worker worker = new Worker();
        worker.setId(1);
        worker.setName("as");
        worker.setSurname("sa");
        worker.setEmail("@");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setMake("sa");
        vehicle.setModel("as");
        vehicle.setYear(1990);
        vehicle.setPlate("GWE 1111");
        vehicle.setCategory("sa");


        when(vehicleRepository.findById(1)).thenReturn(Optional.of(vehicle));

        when(userService.GetUser("1")).thenReturn(user);
        when(workerService.GetWorkerById("1")).thenReturn(worker);


        Rental res = rentalService.CreateRental(1,1,1,1683703611000L,1715326005000L);
        assertNotNull(res);
    }
}