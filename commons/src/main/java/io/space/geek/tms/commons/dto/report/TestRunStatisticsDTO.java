package io.space.geek.tms.commons.dto.report;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestRunStatisticsDTO {

    private long total;

    private long passed;

    private long failed;

    private long skipped;

    private long started;

    private long aborted;

    private List<String> failedTestNames;

    private Map<String, Map<String, Long>> failedTypesSummary;

    private Map<String, Long> failedFeaturesSummary;

    private Map<String, Long> exceptionTypesSummary;

}
