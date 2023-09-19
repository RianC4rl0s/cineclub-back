package com.cineclub.cineclubback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import com.cineclub.cineclubback.dtos.MovieDto;
import com.cineclub.cineclubback.dtos.SearchMovieDto;
import com.cineclub.cineclubback.dtos.UserDto;
import com.cineclub.cineclubback.entity.Movie;
import com.cineclub.cineclubback.services.MovieService;

import jakarta.transaction.Transactional;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findMovieByIdPath(@PathVariable Integer id){
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @PostMapping("/search")
    @Transactional
    public ResponseEntity<List<MovieDto>> findMoviesByName(@RequestBody SearchMovieDto dto){
        List <Movie> movieList = movieService.findByName(dto.movieName);

        List<MovieDto> movieDtos = movieList.stream().map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(movieDtos);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<MovieDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<MovieDto> list = movieService.findAllPaged(pageRequest);

		return ResponseEntity.ok().body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid MovieDto dto){
        
        Movie response = movieService.create(mapper.map(dto, Movie.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(response, MovieDto.class));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid MovieDto dto){
        Movie m = mapper.map(dto, Movie.class);
        try {
            movieService.update(id, m);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(m, MovieDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Filme deletado com sucesso");
    }

}
