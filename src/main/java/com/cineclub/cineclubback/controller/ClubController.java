package com.cineclub.cineclubback.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cineclub.cineclubback.dtos.ClubCreateDto;
import com.cineclub.cineclubback.dtos.ClubDto;
import com.cineclub.cineclubback.dtos.UserDto;
import com.cineclub.cineclubback.entity.Club;
import com.cineclub.cineclubback.entity.Movie;
import com.cineclub.cineclubback.entity.User;
import com.cineclub.cineclubback.services.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @Autowired
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClubDto>> findAll() {
        List<Club> clubList = clubService.findAll();
        List<ClubDto> clubDtoList = clubList.stream()
                .map(club -> mapper.map(club, ClubDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(clubDtoList);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ClubDto>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClubDto> list = clubService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<ClubDto> createClub(@RequestBody @Valid ClubCreateDto dto) {
        Club club = mapper.map(dto, Club.class);
        Club response = clubService.createClub(club);
        ClubDto responseDto = mapper.map(response, ClubDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/{clubId}/addUser/{userId}")
    public ResponseEntity<ClubDto> addUserToClub(@PathVariable Integer clubId, @PathVariable Integer userId) {
        User user = new User();
        user.setId(userId);
        Club response = clubService.addUser(user, clubId);
        ClubDto responseDto = mapper.map(response, ClubDto.class);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{clubId}/removeUser/{userId}")
    public ResponseEntity<ClubDto> removeUserFromClub(@PathVariable Integer clubId, @PathVariable Integer userId) {
        User user = new User();
        user.setId(userId);
        Club response = clubService.removeUser(user, clubId);
        ClubDto responseDto = mapper.map(response, ClubDto.class);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/{clubId}/addMovie/{movieId}")
    public ResponseEntity<ClubDto> addMovieToClub(@PathVariable Integer clubId, @PathVariable Integer movieId) {
        Movie movie = new Movie();
        movie.setId(movieId);
        Club response = clubService.addMovie(movie, clubId);
        ClubDto responseDto = mapper.map(response, ClubDto.class);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{clubId}/removeMovie/{movieId}")
    public ResponseEntity<ClubDto> removeMovieFromClub(@PathVariable Integer clubId, @PathVariable Integer movieId) {
        Movie movie = new Movie();
        movie.setId(movieId);
        Club response = clubService.removeMovie(movie, clubId);
        ClubDto responseDto = mapper.map(response, ClubDto.class);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Integer id) {
        clubService.delete(id);
        return ResponseEntity.ok().body("Clube deletado com sucesso");
    }
}
