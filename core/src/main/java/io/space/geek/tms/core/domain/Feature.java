package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(schema = "tms_core", name = "features")
public class Feature extends BaseEntity {

    private String name;

    private Long parentId;

    private Long projectId;

    @Builder.Default
    @OneToOne(cascade = CascadeType.ALL)
    private FeatureDetails details = FeatureDetails.builder().build();

    @JoinColumn(name = "feature_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FeatureSpecification> specifications;

    private Long createdBy;

    @Builder.Default
    private Boolean automationApplicationMap = Boolean.FALSE;

    private String businessDomain;

    private String businessArea;

    @Builder.Default
    private Boolean released = Boolean.FALSE;

    private LocalDateTime releasedOn;

    private String product_specs;

    private String tech_specs;

    private String test_strategy;
}
