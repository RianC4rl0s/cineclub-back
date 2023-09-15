package com.cineclub.cineclubback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cineclub.cineclubback.entity.Club;

public interface ClubRepository extends JpaRepository<Club,Integer> {
    
}
