package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.constant.tms.ProjectType;
import io.space.geek.tms.commons.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(schema = "tms_core", name = "projects")
public class Project extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    private String tabs;

    private String description;

    private String imagePath;

    private String testStoryTemplate;

    private String testStrategyTemplate;
}
