package io.space.geek.tms.commons.dto.report;

import io.qameta.allure.entity.TestResult;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestRunDTO {

    private String id;

    @NotNull
    private Long projectId;

    @NotNull
    private Long featureId;

    @NotEmpty
    private String name;

    private String description;

    private String tags;

    private Integer status;

    private LocalDateTime started;

    private LocalDateTime finished;

    private Integer duration;

    private String meta;

    private boolean manual;

    private String sendTo;

    private TestRunConfigurationDTO configuration;

    private TestRunStatisticsDTO statistics;

    private Set<TestResult> results;

}
