package com.cineclub.cineclubback.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cineclub.cineclubback.dtos.ClubDto;
import com.cineclub.cineclubback.dtos.UserDto;
import com.cineclub.cineclubback.entity.Club;
import com.cineclub.cineclubback.entity.Movie;
import com.cineclub.cineclubback.entity.User;
import com.cineclub.cineclubback.exception.DataException;
import com.cineclub.cineclubback.exception.UserNotFoundException;
import com.cineclub.cineclubback.repositories.ClubRepository;

@Service
public class ClubService {

    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    ModelMapper mapper;

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Page<ClubDto> findAllPaged(PageRequest pageRequest) {
        Page<Club> list = clubRepository.findAll(pageRequest);
        return list.map(it -> mapper.map(it, ClubDto.class));
    }

    public Club createClub(Club club) {

        club.setAdmUser(userService.findUserById(club.getAdmUser().getId()));
        club.getAdmUser().getClubs().add(club);
        club.setUsers(new ArrayList<User>());
        club.getUsers().add(userService.findUserById(club.getAdmUser().getId()));
        Club response = clubRepository.save(club);
        return response;
    }

    public Club addUser(User u, Integer clubId) {
        try {
            Club response = clubRepository.findById(clubId)
                    .orElseThrow(() -> new UserNotFoundException("No user by email: " + clubId));

            try {
                User userResp = userService.findUserById(u.getId());
                response.getUsers().add(userResp);
                userResp.getClubs().add(response);

                response = clubRepository.save(response);

                return response;
            } catch (Exception e) {
                throw new UserNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    public Club removeUser(User u, Integer clubId) {
        try {
            Club response = clubRepository.findById(clubId)
                    .orElseThrow(() -> new UserNotFoundException("No user by email: " + clubId));
            response.getUsers().removeIf(element -> element.getId().equals(u.getId()));
            return response;

        } catch (Exception e) {
            // TODO: handle exception
            throw new UserNotFoundException(e.getMessage());
        }
    }

    public Club addMovie(Movie movie, Integer clubId) {
        try {
            Club club = clubRepository.findById(clubId)
                    .orElseThrow(() -> new UserNotFoundException("Clube não encontrado: " + clubId));

            Movie movieResponse = movieService.findById(movie.getId());
            club.getMovies().add(movieResponse);
            movieResponse.getClubs().add(club);

            club = clubRepository.save(club);

            return club;
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    public Club removeMovie(Movie movie, Integer clubId) {
        try {
            Club club = clubRepository.findById(clubId)
                    .orElseThrow(() -> new UserNotFoundException("Clube não encontrado: " + clubId));

            club.getMovies().removeIf(element -> element.getId().equals(movie.getId()));

            club = clubRepository.save(club);

            return club;
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    public void delete(Integer id) {

        try {
            Club response = clubRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Club Not found"));
            clubRepository.delete(response);

        } catch (EmptyResultDataAccessException e) {

            throw new UserNotFoundException("Club not exist");
        } catch (DataIntegrityViolationException e) {
            throw new DataException("Cannot dell Club");
            // throw new Exception("Cannot dell user");
        }

    }
}
