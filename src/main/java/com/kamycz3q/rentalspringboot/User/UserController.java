package com.kamycz3q.rentalspringboot.User;


import com.kamycz3q.rentalspringboot.Rental.Rental;
import com.kamycz3q.rentalspringboot.Rental.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final RentalService rentalService;
    @Autowired
    public UserController(UserService userService, RentalService rentalService) {
        this.userService = userService;
        this.rentalService = rentalService;
    }

    //listowanie uzytkownikow
    @GetMapping
    public List<User> ListUsers() {
        return userService.ListUsers();
    }
    @GetMapping("/{id}")
    public User GetUser(@PathVariable(name = "id") String id) {
        return userService.GetUser(id);
    }


    //tworzenie uzytkownika
    public record CreateUserRequest(
            String name,
            String surname,
            String email
    ){}
    @PostMapping
    public User CreateUser(@RequestBody CreateUserRequest addUserRequest) {
        return userService.CreateUser(
                addUserRequest.name(),
                addUserRequest.surname(),
                addUserRequest.email()
        );
    }

    //deletowanie uzytkownika
    @DeleteMapping("/{id}")
    public boolean DeleteUser(@PathVariable(name = "id") String id) {
        return userService.DeleteUser(id);
    }

    //updateowanie uzytkownika
    public record UpdateUserRequest(
            String name,
            String surname,
            String email
    ) {}
    @PatchMapping("/{id}")
    public User UpdateUser(@PathVariable(name = "id") String id, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.UpdateUser(
                id,
                updateUserRequest.name(),
                updateUserRequest.surname(),
                updateUserRequest.email()
        );
    }

    @GetMapping("/rentals/{id}")
    public List<Rental> GetRentals(@PathVariable(name = "id") String userId) {
        return rentalService.GetRentalsForUserId(userId);
    }


}
