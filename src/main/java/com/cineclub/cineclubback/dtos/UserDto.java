package com.cineclub.cineclubback.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

import lombok.Data;

@Data
public class UserDto {
    
    private Integer id;
    @Max(80)
    private String nome;
    private String senha;
    
    private String user;    
    private Integer idade;
    @Column(length = 1000)
    private String descricao;
}
