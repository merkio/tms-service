package com.company.tms.commons.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;
}
