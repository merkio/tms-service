package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.constant.tms.PriorityTestCase;
import io.space.geek.tms.commons.constant.tms.TestStoryType;
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
@Table(schema = "tms_core", name = "test_cases")
public class TestCase extends BaseEntity {

    private String title;

    private String description;

    private Long acceptanceCriteriaId;

    @Enumerated(EnumType.ORDINAL)
    private PriorityTestCase priority;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private TestStoryType type = TestStoryType.MANUAL;

    @Builder.Default
    private Boolean automated = Boolean.FALSE;

    private String labels;

    private Long createdBy;

    private Long featureId;

    private String automationTemplate;

    private Integer orderId;
}
