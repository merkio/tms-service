package com.company.tms.commons.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class NamedBaseDocument extends BaseDocument {

    protected String name;
}
