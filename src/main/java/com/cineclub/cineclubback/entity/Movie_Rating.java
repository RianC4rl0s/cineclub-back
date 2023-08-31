package com.cineclub.cineclubback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie_rating")
@Getter @Setter @AllArgsConstructor
public class Movie_Rating {
    @Id
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    
    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
    
    Double rate;
}
