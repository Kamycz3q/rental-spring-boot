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

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RentalServiceTest {
    @Mock
    private WorkerRepository workerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LogService logService;
    @Mock
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private WorkerService workerService;
    @InjectMocks
    private  VehicleService vehicleService;
    @InjectMocks
    private UserService userService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    final void testGetRental() {
        Rental rental = new Rental();
        rental.setWorkerId(1);
        rental.setState(true);
        rental.setPeriodStart(new Timestamp(1683703611000L));
        rental.setPeriodEnd(new Timestamp(1715326005000L));

        when(rentalRepository.findById(anyInt())).thenReturn(Optional.of(rental));
        System.out.println(rental);
        System.out.println(rentalService.GetRental("1"));
        Rental res = rentalService.GetRental("1");
        assertNotNull(res);
    }


}