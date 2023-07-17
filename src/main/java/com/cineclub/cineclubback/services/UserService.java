package com.cineclub.cineclubback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cineclub.cineclubback.entity.User;
import com.cineclub.cineclubback.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> findAll (){
        return userRepository.findAll();
    }

    public User create(User user){

        User response = userRepository.save(user);

        return response;

    }
}
