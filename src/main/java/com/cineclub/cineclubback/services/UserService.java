package com.cineclub.cineclubback.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cineclub.cineclubback.dtos.UserDto;
import com.cineclub.cineclubback.entity.User;
import com.cineclub.cineclubback.exception.DataException;

import com.cineclub.cineclubback.exception.UserNotFoundException;
import com.cineclub.cineclubback.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    public User findUserById(Integer id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("No user by ID: " + id));

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("No user by ID: " + id);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<UserDto> findAllPaged(PageRequest pageRequest) {
        Page<User> list = userRepository.findAll(pageRequest);
        return list.map(it -> mapper.map(it, UserDto.class));
    }

    public User create(User user) {

        User response = userRepository.save(user);

        return response;

    }

    public User update(Integer id, User dto) throws Exception {
        User u = userRepository.findById(dto.getId()).orElseThrow(() -> new Exception("não foi encontrado"));

        u.setName(dto.getName());
        u.setPassword(dto.getPassword());
        u.setAge(dto.getAge());
        u.setBio(dto.getBio());
        u.setUsername(dto.getUsername());

        User response = userRepository.save(u);

        return response;
    }

    public void delete(Integer id) {

        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user Not found"));
            userRepository.delete(user);

        } catch (EmptyResultDataAccessException e) {

            throw new UserNotFoundException("User not exist");
        } catch (DataIntegrityViolationException e) {
            throw new DataException("Cannot dell User");
            // throw new Exception("Cannot dell user");
        }

    }

}
