package com.cineclub.cineclubback.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_tb")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length = 80)
    private String name;
    private String password;
    @Column(unique = true, name = "username")
    private String username;
    @Column(unique = true)
    private String email;
    private Integer age;
    @Column(length = 1000)
    private String bio;


    
}
