package io.space.geek.tms.commons.dto.tms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSuiteDTO {

    private Integer id;
    private String name;
    private Integer projectId;
    private Boolean enabled;
    private String testNames;
}
