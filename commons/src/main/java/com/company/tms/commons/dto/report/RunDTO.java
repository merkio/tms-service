package com.company.tms.commons.dto.report;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RunDTO {

    private Long id;

    private Integer projectId;

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

    private Boolean manual;

    private String sendTo;

    private RunConfigurationDTO configuration;

    private RunStatisticsDTO statisticsDTO;

}
