package io.space.geek.tms.commons.dto.tms;

import io.space.geek.tms.commons.constant.tms.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private ProjectType type;

    private String description;

    private String imagePath;

    private List<Long> members;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
