package com.cineclub.cineclubback.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import com.cineclub.cineclubback.dtos.MovieDto;
import com.cineclub.cineclubback.entity.Movie;
import com.cineclub.cineclubback.exception.DataException;
import com.cineclub.cineclubback.exception.MovieNotFoundException;
import com.cineclub.cineclubback.repositories.MovieRepository;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ModelMapper mapper;

    public Movie findById(Integer id) {
        try {
            return movieRepository.findById(id)
                    .orElseThrow(() -> new MovieNotFoundException("No movie with ID: " + id));
        } catch (MovieNotFoundException e) {
            throw new MovieNotFoundException(e.getMessage());
        }
    }

    public List<Movie> findAll() {

        return movieRepository.findAll();

    }

    public List<Movie> fidByName(String sub) {

        return movieRepository.findByNameContaining(sub);

    }

    public Page<MovieDto> findAllPaged(PageRequest pageRequest) {
        Page<Movie> list = movieRepository.findAll(pageRequest);
        return list.map(it -> mapper.map(it, MovieDto.class));
    }

    public Movie create(Movie movie) {

        Movie response = movieRepository.save(movie);

        return response;

    }

    public Movie update(Integer id,Movie movie){
        Movie m = movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException("Movie not found with id:" + id));
        m.setName(movie.getName());
        m.setDirector(movie.getDirector());
        m.setReleaseDate(movie.getReleaseDate());
        m.setSynopsis(m.getSynopsis());

        Movie response = movieRepository.save(m);

        return response;
    }

    public void delete(Integer id) {

        try {
            Movie movie = movieRepository.findById(id)
                    .orElseThrow(() -> new MovieNotFoundException("movie Not found" + id));
            movieRepository.delete(movie);

        } catch (EmptyResultDataAccessException e) {

            throw new MovieNotFoundException("Movie not exist" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataException("Cannot dell Movie" + id);
            // throw new Exception("Cannot dell user");
        }

    }
}
