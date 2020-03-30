package com.company.tms.commons.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResetPasswordDTO {

    @NotNull
    private String email;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
