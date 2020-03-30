package com.company.tms.commons.dto.report;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultDTO {

    private Long id;

    private Long testRunId;

    private Integer projectId;

    private String name;

    private String browserName;

    private String browserVersion;

    private String environment;

    private String failureType;

    private String manualFailureDecision;

    private String failureName;

    private String failureMessage;

    private String tags;

    private String parameters;

    private Integer status;

    private String title;

    private String description;

    private String feature;

    private String story;

    private String issue;

    private String owner;

    private LocalDateTime started;

    private LocalDateTime finished;

    private Integer duration;

    private Integer severity;

    private String thread;

    private String comment;

    private String testPackagePath;

    private Integer testStoryId;

    private Boolean archived;

    private String traceId;

    private ReportAttachmentDTO attachmentDTO;

}
