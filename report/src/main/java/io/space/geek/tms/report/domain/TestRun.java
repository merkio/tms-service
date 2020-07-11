package io.space.geek.tms.report.domain;


import io.space.geek.tms.commons.domain.BaseDocument;
import io.space.geek.tms.commons.dto.report.TestResult;
import io.space.geek.tms.commons.dto.report.TestRunConfigurationDTO;
import io.space.geek.tms.commons.dto.report.TestRunStatisticsDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test_runs")
@EqualsAndHashCode(callSuper = true)
public class TestRun extends BaseDocument {

    private Long projectId;

    private Long featureId;

    private String name;

    private String description;

    private String tags;

    private Integer status;

    private LocalDateTime started;

    private LocalDateTime finished;

    private Integer duration;

    private String meta;

    private String environment;

    private boolean manual;

    private String sendTo;

    private TestRunConfigurationDTO configuration;

    private TestRunStatisticsDTO statistics;

    private Set<TestResult> results;
}
