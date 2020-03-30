package com.company.tms.commons.dto.report;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RunConfigurationDTO {

    private Integer id;

    private String url;

    private String branchName;

    private String environment;

    private String platform;

    private String browser;

    private String browserVersion;
}
