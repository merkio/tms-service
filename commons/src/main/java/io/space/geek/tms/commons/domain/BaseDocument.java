package io.space.geek.tms.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDocument {

    @Id
    protected String id;

    @CreatedDate
    protected LocalDateTime createdOn;

    @LastModifiedDate
    protected LocalDateTime updatedOn;
}
