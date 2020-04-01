package com.company.tms.auth.service;

import com.company.tms.auth.document.User;
import com.company.tms.auth.exception.UserNotFoundException;
import com.company.tms.auth.repository.UserRepository;
import com.company.tms.auth.util.EntityAdapter;
import com.company.tms.commons.dto.auth.SignUpRequestDTO;
import com.company.tms.commons.dto.auth.UserDTO;
import com.company.tms.commons.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll().stream().map(EntityAdapter::toDTO).collect(Collectors.toList());
    }

    public UserDTO addUser(SignUpRequestDTO signUpRequestDTO) {
        log.info("Add new user with username [{}] and email [{}]", signUpRequestDTO.getUsername(), signUpRequestDTO.getEmail());
        return EntityAdapter.toDTO(userRepository.save(EntityAdapter.fromDTO(signUpRequestDTO)));
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        log.info("Update user with id [{}]", id);

        User currentUser = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with id [{}] does not exist", id));
        BeanUtils.copyNonNullProperties(EntityAdapter.fromDTO(userDTO), currentUser);

        log.info("Successfully updated user with id [{}]", currentUser.getId());
        return EntityAdapter.toDTO(currentUser);
    }

    public UserDTO getUser(String id) {
        log.info("Get user with id [{}]", id);
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with id [{}] does not exist", id));
        return EntityAdapter.toDTO(user);
    }

    public void delete(String id) {
        log.info("Remove user with id [{}]", id);
        userRepository.deleteById(id);
    }
}
