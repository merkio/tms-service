package com.company.tms.commons.dto.report;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestAutomationLogDTO {

    private Long id;

    private Long testResultId;

    private String logEntry;

    private LocalDateTime timeStamp;

}
