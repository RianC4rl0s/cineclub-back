package com.cineclub.cineclubback.entity;

import java.util.List;
import java.util.Set;

import com.cineclub.cineclubback.entity.enums.Genre;

import java.time.LocalDate;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Lob
    private String synopsis;
    private String director;
    private LocalDate releaseDate;
    @Lob
    private String banner;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    //private Double rating;


    @OneToMany(mappedBy = "movie")
    Set<Movie_Rating> rating;

    @ManyToMany(mappedBy = "movies")
    List<Club> clubs;

}
