package io.space.geek.tms.commons.dto.tms;

import io.space.geek.tms.commons.constant.tms.FeatureSpecificationSource;
import io.space.geek.tms.commons.constant.tms.FeatureSpecificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureSpecificationDTO {

    private Long id;
    private FeatureSpecificationSource source;
    private FeatureSpecificationType type;
    private String contentId;
    private Integer version;
    private String url;
}
