package org.fiipractic.service;

import org.fiipractic.dto.UserDTO;
import org.fiipractic.model.User;
import org.fiipractic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fiipractic.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(User userFromRq) {
        userRepository.create(userFromRq);
    }

    public List<UserDTO> getAll() {
        return userRepository.getAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findByUserName(String userName) {
        return Optional.ofNullable(userRepository.findByUserName(userName))
                .map(this::toDTO)
                .orElse(null);
    }

    public UserDTO findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id))
                .map(this::toDTO)
                .orElseThrow(()-> new NotFoundException(User.class.getName(),id));
    }

    private UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setEmail(user.getEmail());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}