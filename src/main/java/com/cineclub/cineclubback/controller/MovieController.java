package com.cineclub.cineclubback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.cineclub.cineclubback.dtos.MovieDto;

import com.cineclub.cineclubback.entity.Movie;
import com.cineclub.cineclubback.services.MovieService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    MovieService movieService;

    @Autowired 
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll(){
        List<Movie> movieList = movieService.findAll();
        
        List<MovieDto> movieDtos = movieList.stream().map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(movieDtos);
    }
    @PostMapping
    public ResponseEntity<MovieDto> create(@RequestBody MovieDto dto){

        Movie response = movieService.create(mapper.map(dto, Movie.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(response, MovieDto.class));

    }


}
