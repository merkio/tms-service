package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.constant.tms.FeatureSpecificationSource;
import io.space.geek.tms.commons.constant.tms.FeatureSpecificationType;
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
@Table(schema = "tms_core", name = "feature_specs")
public class FeatureSpecification extends BaseEntity {

    private String url;

    private Integer version;

    private String contentId;

    @Enumerated(EnumType.STRING)
    private FeatureSpecificationType type;

    @Enumerated(EnumType.STRING)
    private FeatureSpecificationSource source;
}
