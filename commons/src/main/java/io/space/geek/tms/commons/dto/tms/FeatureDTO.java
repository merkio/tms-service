package io.space.geek.tms.commons.dto.tms;

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
public class FeatureDTO {

    private Long id;

    private Long parentId;

    private String attachments;

    private String name;

    private Long projectId;

    private String createdBy;

    private LocalDateTime createdOn;

    private Boolean released;

    private LocalDateTime releasedOn;

    private LocalDateTime updatedOn;

    private FeatureDetailsDTO details;

    private List<FeatureSpecificationDTO> specifications;

    private String businessDomain;

    private Long businessAreaId;

    private String product_specs;

    private String tech_specs;

    private String test_strategy;
}
