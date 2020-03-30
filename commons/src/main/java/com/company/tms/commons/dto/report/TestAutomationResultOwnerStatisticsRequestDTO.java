package com.company.tms.commons.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestAutomationResultOwnerStatisticsRequestDTO {

    @NonNull
    private String owner;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;

}
