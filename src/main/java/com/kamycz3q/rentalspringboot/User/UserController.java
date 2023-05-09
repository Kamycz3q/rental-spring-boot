package com.kamycz3q.rentalspringboot.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //listowanie uzytkownikow
    @GetMapping
    public List<User> ListUsers() {
        return userService.ListUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> GetUser(@PathVariable(name = "id") String id) {
        return userService.GetUser(id);
    }


    //tworzenie uzytkownika
    public record CreateUserRequest(
            String name,
            String surname,
            String email
    ){}
    @PostMapping
    public void CreateUser(@RequestBody CreateUserRequest addUserRequest) {
        userService.CreateUser(
                addUserRequest.name(),
                addUserRequest.surname(),
                addUserRequest.email()
        );
    }

    //deletowanie uzytkownika
    @DeleteMapping("/{id}")
    public void DeleteUser(@PathVariable(name = "id") String id) {
        userService.DeleteUser(id);
    }

    public record UpdateUserRequest(
            String name,
            String surname,
            String email
    ) {}
    @PatchMapping("/{id}")
    public void UpdateUser(@PathVariable(name = "id") String id, @RequestBody UpdateUserRequest updateUserRequest) {
        userService.UpdateUser(
                id,
                updateUserRequest.name(),
                updateUserRequest.surname(),
                updateUserRequest.email()
        );
    }


}
