package com.company.tms.auth.controller;

import com.company.tms.auth.document.User;
import com.company.tms.auth.document.UserRole;
import com.company.tms.auth.repository.UserRepository;
import com.company.tms.commons.dto.auth.SignUpRequestDTO;
import com.company.tms.commons.dto.auth.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getUsersByAdmin() throws Exception {
        userRepository.save(User.builder()
                                .username("Username")
                                .email("user@example.com")
                                .password(passwordEncoder.encode("test"))
                                .roles(Set.of(UserRole.ROLE_USER.name()))
                                .build());

        List result = objectMapper.readValue(
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/users/")
                                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), List.class);

        assertEquals(1, result.size());

        System.out.println(result);
    }

    @Test
    @WithMockUser
    void getUsersByUser() throws Exception {
        userRepository.save(User.builder()
                                .username("Username")
                                .email("user@example.com")
                                .password(passwordEncoder.encode("test"))
                                .roles(Set.of(UserRole.ROLE_USER.name()))
                                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/users")
                            .accept(MediaType.ALL))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void signUpNewUser() throws Exception {
        SignUpRequestDTO signUpRequestDTO = SignUpRequestDTO.builder()
            .username("Username")
            .email("user@example.com")
            .password(passwordEncoder.encode("test"))
            .build();

        UserDTO result = objectMapper.readValue(
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/user")
                                .content(objectMapper.writeValueAsString(signUpRequestDTO))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), UserDTO.class);

        assertNotNull(result.getId());
        assertEquals(result.getRoles(), Set.of(UserRole.ROLE_USER.name()));
        assertEquals(result.getEmail(), signUpRequestDTO.getEmail());
        assertEquals(result.getUsername(), signUpRequestDTO.getUsername());
    }
}
