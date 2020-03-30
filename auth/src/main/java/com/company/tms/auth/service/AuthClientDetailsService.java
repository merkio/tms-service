package com.company.tms.auth.service;

import com.company.tms.auth.document.AuthClientDetails;
import com.company.tms.auth.exception.ClientAlreadyExistsException;
import com.company.tms.auth.exception.ClientRegistrationException;
import com.company.tms.auth.exception.NoSuchClientException;
import com.company.tms.auth.repository.AuthClientDetailsRepository;
import com.company.tms.auth.util.EntityAdapter;
import com.company.tms.commons.dto.auth.ClientDetailsDTO;
import com.company.tms.commons.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private final AuthClientDetailsRepository clientDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info("Load client by client id [{}]", clientId);
        Optional<AuthClientDetails> clientDetailsO = clientDetailsRepository.findByClientId(clientId);

        if (clientDetailsO.isEmpty()) {
            throw new ClientRegistrationException("Client with id [{}] not found", clientId);
        }
        return clientDetailsO.get();
    }

    public ClientDetailsDTO addClientDetails(ClientDetailsDTO clientDetailsDTO) {
        log.info("Add clientDetails with clientId [{}]", clientDetailsDTO.getClientId());
        AuthClientDetails authClientDetails = EntityAdapter.fromDTO(clientDetailsDTO);

        AuthClientDetails savedAuthClientDetails = clientDetailsRepository.save(authClientDetails);
        log.info("Successfully saved clientDetails [{}] with clientId [{}]",
                 savedAuthClientDetails.getId(), clientDetailsDTO.getClientId());
        return EntityAdapter.toDTO(savedAuthClientDetails);
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        if (Objects.isNull(loadClientByClientId(clientDetails.getClientId()))) {
            throw new ClientAlreadyExistsException(String.format("Client with id %s already existed", clientDetails.getClientId()));
        }
        AuthClientDetails authClientDetails =
            new AuthClientDetails(clientDetails.getClientId(), clientDetails.getResourceIds(),
                                  clientDetails.isSecretRequired(),
                                  passwordEncoder.encode(clientDetails.getClientSecret()),
                                  clientDetails.isScoped(),
                                  clientDetails.getScope(),
                                  clientDetails.getAuthorizedGrantTypes(),
                                  clientDetails.getRegisteredRedirectUri(),
                                  clientDetails.getAuthorities(),
                                  clientDetails.getAccessTokenValiditySeconds(),
                                  clientDetails.getRefreshTokenValiditySeconds(),
                                  clientDetails.isAutoApprove("true"),
                                  clientDetails.getAdditionalInformation());
        clientDetailsRepository.save(authClientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        log.info("Update clientDetails with clientId [{}]", clientDetails.getClientId());
        Optional<AuthClientDetails> clientDetailsO = clientDetailsRepository.findByClientId(clientDetails.getClientId());

        if (clientDetailsO.isEmpty()) {
            throw new NoSuchClientException("Client with clientId [{}] not found", clientDetails.getClientId());
        }

        AuthClientDetails currentClientDetails = clientDetailsO.get();
        BeanUtils.copyNonNullProperties(clientDetails, currentClientDetails);
        clientDetailsRepository.save(currentClientDetails);

        log.info("Successfully updated clientDetails [{}] with clientId [{}]",
                 currentClientDetails.getId(), currentClientDetails.getClientId());
    }

    public ClientDetailsDTO updateClientDetails(String id, ClientDetailsDTO clientDetailsDTO) {
        log.info("Update clientDetails with id [{}]", id);
        Optional<AuthClientDetails> currentClientDetailsO = clientDetailsRepository.findById(id);

        if (currentClientDetailsO.isEmpty()) {
            throw new NoSuchClientException("Client with id [{}] not found", id);
        }

        AuthClientDetails currentClientDetails = currentClientDetailsO.get();
        BeanUtils.copyNonNullProperties(clientDetailsDTO, currentClientDetails);

        clientDetailsRepository.save(currentClientDetails);
        log.info("Successfully updated clientDetails [{}] with clientId [{}]",
                 currentClientDetails.getId(), currentClientDetails.getClientId());
        return EntityAdapter.toDTO(currentClientDetails);
    }

    @Override
    public void updateClientSecret(String clientId, String clientSecret) throws NoSuchClientException {
        log.info("Update client secret in clientDetails with clientId [{}]", clientId);
        AuthClientDetails currentClientDetails = clientDetailsRepository.findByClientId(clientId)
            .orElseThrow(() -> new NoSuchClientException("Client with clientId [{}] not found", clientId));

        currentClientDetails.setClientSecret(passwordEncoder.encode(clientSecret));
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        log.info("Remove clientDetails with clientId [{}]", clientId);
        clientDetailsRepository.delete(
            clientDetailsRepository.findByClientId(clientId)
                .orElseThrow(() -> new NoSuchClientException("Client with clientId [{}] not found", clientId)));
    }

    public void removeClientDetailsById(String id) throws NoSuchClientException {
        log.info("Remove clientDetails with id [{}]", id);
        clientDetailsRepository.delete(
            clientDetailsRepository.findById(id)
                .orElseThrow(() -> new NoSuchClientException("Client with id [{}] not found", id)));
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return new ArrayList<>(clientDetailsRepository.findAll());
    }

    public List<ClientDetailsDTO> getAllClientDetails() {
        log.info("Get all clientDetails");
        return clientDetailsRepository.findAll().stream()
            .map(EntityAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public ClientDetailsDTO getClientDetails(String id) {
        log.info("Get clientDetails with id [{}]", id);
        return EntityAdapter.toDTO(clientDetailsRepository.findById(id)
                                       .orElseThrow(() -> new NoSuchClientException("Client with id [{}] not found", id)));
    }

}