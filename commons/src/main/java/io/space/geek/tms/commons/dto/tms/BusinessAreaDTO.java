package io.space.geek.tms.commons.dto.tms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessAreaDTO {

    private String id;

    private String name;

    private String businessDomain;

}
