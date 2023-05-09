package com.kamycz3q.rentalspringboot.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //listowanie all uzytkownikow
    public List<User> ListUsers() {
        return userRepository.findAll();
    }
    //dane uzytkownika o podanym id
    public Optional<User> GetUser(String id) {
        return  userRepository.findById(Integer.parseInt(id));
    }
    //tworzenie uzytkownika
    public void CreateUser(String name, String surname, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        userRepository.save(newUser);
    }

    //usuwanie uzytkownika
    public void DeleteUser(String id) {
        userRepository.deleteById(Integer.parseInt(id));

    }
    //updateowanie uzytkownika
    public void UpdateUser(String id, String name, String surname, String email) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        userRepository.save(user);

    }
}
