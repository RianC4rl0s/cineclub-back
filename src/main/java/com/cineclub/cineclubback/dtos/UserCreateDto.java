package com.cineclub.cineclubback.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserCreateDto {
    Integer id;
    @NotBlank(message = "The name can't be blank")
    @Max(80)
    private String name;
    @NotBlank(message = "The passoword can't be blank")
    private String passoword;
    @Pattern(regexp = "@\\\\w{1,20}",message = "The username need has 1 -20 characteres")
    @NotBlank(message = "Username can't be blank")
    private String username;    
    @Min(12)
    private Integer age;
    @Column(length = 1000)
    private String bio;
}
