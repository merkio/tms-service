package io.space.geek.tms.commons.dto.tms;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Data to launch tests on executor")
public class LaunchDTO {

    private String projectId;

    private String testRunId;

    private String featureId;

    private String launchName;

    private List<String> testStories;

    private String tags;

    @Builder.Default
    private boolean manual = true;

    private TestRunConfigurationDTO configuration;
}
