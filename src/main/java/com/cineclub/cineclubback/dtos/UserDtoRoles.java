package com.cineclub.cineclubback.dtos;

import com.cineclub.cineclubback.entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDtoRoles {
    
     Integer id;
    @NotBlank(message = "The name can't be blank")
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 80, message = "{validation.name.size.too_long}")
    private String name;
    //@NotBlank(message = "The passoword can't be blank")
    //private String password;
    @Pattern(regexp = "^@[\\w\\d]{1,30}$", message = "username must start with @ and be between 1 and 20 characters long")
    @NotBlank(message = "Username can't be blank")
    private String username;
    @NotBlank(message = "The email can't be blank")
    @Email(message = "Please use a valid email")
    private String email;
    @Min(12)
    private Integer age;
    @Size(max = 1000)
    private String bio;

    private UserRole role;

}
