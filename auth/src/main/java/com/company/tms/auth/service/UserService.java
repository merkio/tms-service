package com.company.tms.auth.service;

import com.company.tms.commons.dto.auth.SignUpRequestDTO;
import com.company.tms.commons.dto.auth.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    public List<UserDTO> getAllUsers() {
        return null;
    }

    public UserDTO addUser(SignUpRequestDTO signUpRequestDTO) {
        return null;
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        return null;
    }

    public UserDTO getUser(String id) {
        return null;
    }

    public void delete(String id) {

    }
}
