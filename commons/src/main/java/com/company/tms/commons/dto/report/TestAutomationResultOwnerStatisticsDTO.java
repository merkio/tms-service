package com.company.tms.commons.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestAutomationResultOwnerStatisticsDTO {

    private String name;

    private Integer count;

    private Integer passed;

    private Integer failed;

    private Integer skipped;

    private Integer passRate;

    private Integer duration;

}
