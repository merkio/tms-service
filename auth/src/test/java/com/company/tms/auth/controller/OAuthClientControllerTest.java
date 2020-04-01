package com.company.tms.auth.controller;

import com.company.tms.auth.document.AuthClientDetails;
import com.company.tms.auth.document.UserRole;
import com.company.tms.auth.repository.AuthClientDetailsRepository;
import com.company.tms.commons.dto.auth.ClientDetailsDTO;
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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OAuthClientControllerTest {


    @Autowired
    private AuthClientDetailsRepository oAuthClientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createClient() throws Exception {
        ClientDetailsDTO clientDetailsDTO = ClientDetailsDTO.builder()
            .clientId("test")
            .clientSecret(passwordEncoder.encode("tms-secret-key"))
            .scope(Set.of("read", "write"))
            .accessTokenValiditySeconds(3600)
            .refreshTokenValiditySeconds(10000)
            .resourceIds(Set.of("tms", "auth"))
            .autoApprove(true)
            .registeredRedirectUri(Set.of("http://localhost/actuator/health"))
            .authorizedGrantTypes(Set.of("authorization_code", "refresh_token", "password", "client_credentials"))
            .roles(Set.of(UserRole.ROLE_USER.name()))
            .build();

        ClientDetailsDTO result = objectMapper.readValue(
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/client_details")
                                .content(objectMapper.writeValueAsString(clientDetailsDTO))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), ClientDetailsDTO.class);

        assertEquals(clientDetailsDTO.getClientId(), result.getClientId());

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

        @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateClient() throws Exception {
            AuthClientDetails clientDetailsDTO = AuthClientDetails.builder()
                .clientId("test")
                .clientSecret(passwordEncoder.encode("tms-secret-key"))
                .scope(Set.of("read", "write"))
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(10000)
                .resourceIds(Set.of("tms", "auth"))
                .autoApprove(true)
                .registeredRedirectUri(Set.of("http://localhost/actuator/health"))
                .authorizedGrantTypes(Set.of("authorization_code", "refresh_token", "password", "client_credentials"))
                .roles(Set.of(UserRole.ROLE_USER.name()))
                .build();

            oAuthClientRepository.save(clientDetailsDTO);

            clientDetailsDTO.setRoles(Set.of(UserRole.ROLE_USER.name(), UserRole.ROLE_ADMIN.name()));

            ClientDetailsDTO result = objectMapper.readValue(
                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/client_details/" + clientDetailsDTO.getId())
                                    .content(objectMapper.writeValueAsString(clientDetailsDTO))
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.ALL))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(), ClientDetailsDTO.class);

            assertEquals(clientDetailsDTO.getClientId(), result.getClientId());
            assertEquals(clientDetailsDTO.getRoles(), result.getRoles());

            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    @Test
    @WithMockUser
    void getClientWithUserRole() throws Exception {
        ClientDetailsDTO oAuthClient = ClientDetailsDTO.builder()
            .clientId("test")
            .clientSecret(passwordEncoder.encode("test secret"))
            .registeredRedirectUri(Set.of("http://localhost:8080/code"))
            .scope(Set.of("read", "write"))
            .accessTokenValiditySeconds(3600)
            .refreshTokenValiditySeconds(10000)
            .resourceIds(Set.of("tms", "report"))
            .authorizedGrantTypes(Set.of("authorization_code", "password", "refresh_token", "implicit"))
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/client_details")
                            .content(objectMapper.writeValueAsString(oAuthClient))
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .accept(MediaType.ALL))
            .andExpect(status().is4xxClientError());
    }
}
