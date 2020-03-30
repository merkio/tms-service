package com.company.tms.auth.document;

import com.company.tms.commons.domain.BaseDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDocument {

    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}