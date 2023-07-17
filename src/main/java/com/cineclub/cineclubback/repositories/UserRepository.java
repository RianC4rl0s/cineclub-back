package com.cineclub.cineclubback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cineclub.cineclubback.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}
