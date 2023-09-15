package com.cineclub.cineclubback.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ClubDto {

    private Integer id;

    private String name;

    private UserDto admUser;

    private MovieDto currentWeekMovie;

    List<UserDto> users;

    List<MovieDto> movies;
}
