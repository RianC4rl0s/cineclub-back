package com.cineclub.cineclubback.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCreateDto {
    Integer id;
    @NotBlank(message = "The name can't be blank")
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 80, message = "{validation.name.size.too_long}")
    private String name;
    @NotBlank(message = "The passoword can't be blank")
    private String password;
    @Pattern(regexp = "^@[\\w\\d]{1,30}$", message = "username must start with @ and be between 1 and 20 characters long")
    @NotBlank(message = "Username can't be blank")
    private String username;
    // @Min(12)
    private Integer age;
    @Size(max = 1000)
    private String bio;
}
