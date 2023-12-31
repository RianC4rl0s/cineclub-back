package com.cineclub.cineclubback.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cineclub.cineclubback.config.security.TokenService;
import com.cineclub.cineclubback.dtos.AuthenticationDto;
import com.cineclub.cineclubback.entity.User;
// import com.cineclub.cineclubback.repositories.UserRepository;
import com.cineclub.cineclubback.dtos.LoginResponseDTO;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    // @Autowired
    // private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // @PostMapping("/register")
    // public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
    //     if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

    //     String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    //     User newUser = new User(data.login(), encryptedPassword, data.role());

    //     this.repository.save(newUser);

    //     return ResponseEntity.ok().build();
    // }
}