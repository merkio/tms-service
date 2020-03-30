package com.company.tms.auth.document;

import com.company.tms.auth.converter.SerializableObjectConverter;
import com.company.tms.commons.domain.BaseDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "refresh_token")
public class RefreshToken extends BaseDocument {

    private String tokenId;
    private OAuth2RefreshToken token;
    private String authentication;

    public OAuth2Authentication getAuthentication() {
        return SerializableObjectConverter.deserialize(authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = SerializableObjectConverter.serialize(authentication);
    }
}