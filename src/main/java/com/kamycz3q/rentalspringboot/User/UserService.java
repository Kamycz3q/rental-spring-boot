package com.kamycz3q.rentalspringboot.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> ListUsers() {
        return userRepository.findAll();
    }
    public Optional<User> GetUser(String id) {
        return  userRepository.findById(Integer.parseInt(id));
    }
    public void CreateUser(String name, String surname, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        userRepository.save(newUser);
    }

    public void DeleteUser(String id) {
        userRepository.deleteById(Integer.parseInt(id));

    }

    public void UpdateUser(String id, String name, String surname, String email) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        userRepository.save(user);

    }
}
