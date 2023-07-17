package com.cineclub.cineclubback.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cineclub.cineclubback.dtos.UserCreateDto;
import com.cineclub.cineclubback.dtos.UserDto;
import com.cineclub.cineclubback.entity.User;
import com.cineclub.cineclubback.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> userList = userService.findAll(); // Obtendo a lista de usuários

        ModelMapper modelMapper = new ModelMapper();

        List<UserDto> userDtoList = userList.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(userDtoList);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreateDto dto) {

        User response = userService.create(mapper.map(dto, User.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(response, UserDto.class));
    }

}
