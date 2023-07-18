package com.cineclub.cineclubback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cineclub.cineclubback.dtos.UserDto;
import com.cineclub.cineclubback.entity.User;
import com.cineclub.cineclubback.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    public User findById(Integer id) throws Exception{

		return userRepository.findById(id).orElseThrow(() -> new Exception("ERRO 404, entity not found"));
	}
    public List<User> findAll() {
        return userRepository.findAll();
    }
	public Page<UserDto> findAllPaged(PageRequest pageRequest){
		Page<User> list  = userRepository.findAll(pageRequest);
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

    public void delete(Integer id) throws Exception {

        try {
            Optional<User> user = userRepository.findById(id);
            userRepository.delete(user.get());

            // } catch (EmptyResultDataAccessException e) {
        } catch (Exception e) {
            throw new Exception("User not exist");
            // } catch (DataIntegrityViolationException e) {
            // // throw new DataException("Cannot dell Convenio");
            // throw new Exception("Cannot dell user");
            // }

        }
    }
}
