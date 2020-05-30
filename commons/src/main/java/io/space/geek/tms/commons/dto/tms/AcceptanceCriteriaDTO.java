package io.space.geek.tms.commons.dto.tms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Acceptance Criteria data")
public class AcceptanceCriteriaDTO {

    private Long id;

    private Long featureId;

    private String name;

    private String attachments;

    private String description;

    private Integer createdBy;

    private LocalDateTime createdOn;

    private Integer orderId;
}
