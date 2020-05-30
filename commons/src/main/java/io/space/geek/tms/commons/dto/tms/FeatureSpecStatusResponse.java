package io.space.geek.tms.commons.dto.tms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureSpecStatusResponse {

    private Boolean upToDate;
    private Integer savedVersion;
    private Integer actualVersion;
}
