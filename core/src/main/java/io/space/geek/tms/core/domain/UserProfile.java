package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(schema = "tms_core", name = "user_profiles")
public class UserProfile extends BaseEntity {

    private String username;

    private String email;

    private String fullName;

    private String slack;

    private String github;
}
