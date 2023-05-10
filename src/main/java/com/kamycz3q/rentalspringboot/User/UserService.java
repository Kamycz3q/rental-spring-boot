package com.kamycz3q.rentalspringboot.User;

import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
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
        if (userRepository.existsById(Integer.valueOf(id))) {
            return  userRepository.findById(Integer.parseInt(id));
        } else {
            throw new ApiRequestException("User with id " + id + " doesn't exists");
        }
    }
    //tworzenie uzytkownika
    public User CreateUser(String name, String surname, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        userRepository.save(newUser);
        return newUser;
    }

    //usuwanie uzytkownika
    public boolean DeleteUser(String id) {
        if (!userRepository.existsById(Integer.valueOf(id))) {
            throw new ApiRequestException("User with id " + id + " doesn't exists");
        }
        userRepository.deleteById(Integer.parseInt(id));
        return true;

    }
    //updateowanie uzytkownika
    public User UpdateUser(String id, String name, String surname, String email) {
        if (!userRepository.existsById(Integer.valueOf(id))) {
            throw new ApiRequestException("User with id " + id + " doesn't exists");
        }
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        userRepository.save(user);
        return user;

    }
}
