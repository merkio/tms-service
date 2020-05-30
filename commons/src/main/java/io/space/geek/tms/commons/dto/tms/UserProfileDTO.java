package io.space.geek.tms.commons.dto.tms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    private String id;

    private String username;

    private String email;

    private String fullName;

    private String slack;

    private String github;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private Boolean enabled;
}
