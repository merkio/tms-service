package io.space.geek.tms.commons.dto.tms;

import io.space.geek.tms.commons.constant.tms.PriorityTestCase;
import io.space.geek.tms.commons.constant.tms.TestStoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDTO {

    private Long id;
    private String title;
    private String attachments;
    private String description;
    private TestStoryType type;
    private Long acceptanceCriteriaId;
    private PriorityTestCase priority;
    private Boolean automated;
    private String labels;
    private Long createdBy;
    private Long featureId;
    private String automationTemplate;
    private LocalDateTime createdOn;
    private Integer orderId;
}
