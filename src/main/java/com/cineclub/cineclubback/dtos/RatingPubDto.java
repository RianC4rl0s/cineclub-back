package com.cineclub.cineclubback.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingPubDto {
    
    private Integer id;

    @NotBlank(message = "Please,part of the experience is evaluating the film you just saw, write something even if it's the basics, just to express yourself ")
    private String description;
    
    private double rating;
    @NotNull(message = "User required")
    private UserDto user;
    @NotNull(message = "Movie required")
    private MovieDto movie;


    //private ClubDto club;

}
