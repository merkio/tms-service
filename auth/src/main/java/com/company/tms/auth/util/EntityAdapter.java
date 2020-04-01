package com.company.tms.auth.util;


import com.company.tms.auth.document.AuthClientDetails;
import com.company.tms.auth.document.User;
import com.company.tms.auth.document.UserRole;
import com.company.tms.commons.dto.auth.ClientDetailsDTO;
import com.company.tms.commons.dto.auth.SignUpRequestDTO;
import com.company.tms.commons.dto.auth.UserDTO;
import com.company.tms.commons.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntityAdapter {

    private final ModelMapper modelMapper;

    @NotNull
    public static ClientDetailsDTO toDTO(@NotNull AuthClientDetails authClientDetails) {
        ClientDetailsDTO clientDetailsDTO = ClientDetailsDTO.builder().build();
        BeanUtils.copyNonNullProperties(authClientDetails, clientDetailsDTO);
        return clientDetailsDTO;
    }

    @NotNull
    public static AuthClientDetails fromDTO(@NotNull ClientDetailsDTO clientDetailsDTO) {
        AuthClientDetails authClientDetails = AuthClientDetails.builder().build();
        BeanUtils.copyNonNullProperties(clientDetailsDTO, authClientDetails);
        return authClientDetails;
    }

    @NotNull
    public static UserDTO toDTO(@NotNull User user) {
        UserDTO userDTO = UserDTO.builder().build();
        BeanUtils.copyNonNullProperties(user, userDTO);
        return userDTO;
    }

    @NotNull
    public static User fromDTO(@NotNull UserDTO userDTO) {
        User user = User.builder().build();
        BeanUtils.copyNonNullProperties(userDTO, user);
        return user;
    }

    @NotNull
    public static User fromDTO(@NotNull SignUpRequestDTO signUpRequestDTO) {
        User user = User.builder().build();
        BeanUtils.copyNonNullProperties(signUpRequestDTO, user);
        user.setRoles(Set.of(UserRole.ROLE_USER.name()));

        return user;
    }
}
