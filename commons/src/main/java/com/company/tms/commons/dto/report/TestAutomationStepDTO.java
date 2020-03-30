package com.company.tms.commons.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestAutomationStepDTO {

    private Long id;
    private String name;
    private int level;
    private int number;
    private String errorMessage;
    private String trace;
    /**
     * indicates if step is flaky
     */
    private boolean flaky;
    private String description;
    private LocalDateTime start;
    private LocalDateTime stop;
    private long testResultId;
    /**
     * Indicates if the step was successfull
     */
    private String status;
    private List<ParameterDTO> parameters;
}
