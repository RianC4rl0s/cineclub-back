package com.cineclub.cineclubback.dtos;

import java.util.List;
import java.util.Set;

import com.cineclub.cineclubback.entity.enums.Genre;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;



@Data
public class MovieDto {
    private Integer id;
    @NotBlank(message = "The movie need to have a name")
    private String name;
    @NotBlank(message = "The movie need to have a synopsis")
    private String synopsis;
    @NotBlank(message = "The movie need to have a director")
    private String director;
    private LocalDate releaseDate;
    private String banner;

    private List<Genre> genres;

    private Double avaregeRating;


}
