package io.space.geek.tms.commons.dto.tms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDetailsDTO {

    private Long id;
    private Long featureId;
    private String teamName;
    private String qaEngineers;
    private String jiraLabel;
    private String reportRecipients;
    private Boolean signOff;
    private Integer signOffBy;
    private LocalDateTime signOffOn;
    private LocalDateTime featureStartsOn;
    private LocalDateTime testCasesReadyOn;
    private LocalDateTime automationTestsReadyOn;
    private LocalDateTime testingStartsOn;
    private LocalDateTime releaseOn;
}
