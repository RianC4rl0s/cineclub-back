package com.cineclub.cineclubback.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Club {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length = 50)
    private String name;

    @ManyToOne
    private User admUser;

    @ManyToOne
    private Movie currentWeekMovie;

    @ManyToMany(mappedBy = "clubs")
    List<User> users;
    
    @ManyToMany
    @JoinTable(name = "club_movies",
    joinColumns = @JoinColumn(name="club_id"),
    inverseJoinColumns = @JoinColumn(name="movie_id"))
    List<Movie> movies;
}
