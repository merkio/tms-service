package com.company.tms.commons.dto.report;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RunStatisticsDTO {

    private Integer total;

    private Integer passed;

    private Integer failed;

    private Integer skipped;

    private Integer started;

    private Integer aborted;

    private List<String> failedTestNames;

    private Map<String, Map<String, Integer>> failedTypesSummary;

    private Map<String, Integer> failedFeaturesSummary;

    private Map<String, Integer> exceptionTypesSummary;

}
