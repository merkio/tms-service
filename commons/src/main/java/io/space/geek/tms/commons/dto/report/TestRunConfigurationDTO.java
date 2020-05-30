package io.space.geek.tms.commons.dto.report;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestRunConfigurationDTO {

    private String url;

    private String branchName;

    private String environment;

    private String platform;

    private String browser;

    private String browserVersion;
}
