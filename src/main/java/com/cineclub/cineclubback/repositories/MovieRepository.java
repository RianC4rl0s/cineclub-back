package com.cineclub.cineclubback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.cineclub.cineclubback.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByNameContaining(String infix);
}
