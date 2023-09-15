package com.cineclub.cineclubback.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClubCreateDto {

    private Integer id;

    @NotBlank(message = "Please the club neet a name")
    private String name;

    @NotNull(message = "Please the club need to have a adm")
    private UserDto admUser;

    List<UserDto> users;

    List<MovieDto> movies;
}
