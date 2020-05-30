package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(schema = "tms_core", name = "feature_details")
public class FeatureDetails extends BaseEntity {

    private Long featureId;

    private String qaEngineers;

    private String teamName;

    private String jiraLabel;

    private String reportRecipients;

    private Boolean signOff;

    private Long signOffBy;

    private LocalDateTime signOffOn;

    private LocalDateTime featureStartsOn;

    private LocalDateTime testCasesReadyOn;

    private LocalDateTime automationTestsReadyOn;

    private LocalDateTime testingStartsOn;

    private LocalDateTime releaseOn;
}
