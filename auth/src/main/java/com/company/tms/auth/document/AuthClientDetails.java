package com.company.tms.auth.document;

import com.company.tms.commons.domain.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "client_details")
public class AuthClientDetails extends BaseDocument implements ClientDetails {

    private String clientId;
    private Set<String> resourceIds;
    private boolean secretRequired;
    private String clientSecret;
    private boolean scoped;
    private Set<String> scope;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private Collection<GrantedAuthority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private boolean autoApprove;
    private Map<String, Object> additionalInformation;

    @Override
    public boolean isAutoApprove(String s) {
        return autoApprove;
    }
}