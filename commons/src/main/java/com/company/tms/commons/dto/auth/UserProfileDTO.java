package com.company.tms.commons.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    private Long id;

    private String username;

    private String email;

    private String fullName;

    private String slack;

    private String github;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private Boolean enabled;

}
