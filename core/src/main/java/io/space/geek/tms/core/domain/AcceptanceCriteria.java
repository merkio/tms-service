package io.space.geek.tms.core.domain;

import io.space.geek.tms.commons.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(schema = "tms_core", name = "acceptance_criteria")
public class AcceptanceCriteria extends BaseEntity {

    private Long featureId;

    private String name;

    private String description;

    @CreatedBy
    private Long createdBy;

    private Integer orderId;
}
