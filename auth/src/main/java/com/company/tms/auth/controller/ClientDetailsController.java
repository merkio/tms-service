package com.company.tms.auth.controller;

import com.company.tms.auth.service.AuthClientDetailsService;
import com.company.tms.commons.client.auth.ClientDetailsApi;
import com.company.tms.commons.dto.auth.ClientDetailsDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientDetailsController implements ClientDetailsApi {

    private final AuthClientDetailsService authClientDetailsService;

    @ApiOperation("Get all client details")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<ClientDetailsDTO> getClientDetails() {
        return authClientDetailsService.getAllClientDetails();
    }

    @ApiOperation("Add a client details")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ClientDetailsDTO addClientDetails(@Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        return authClientDetailsService.addClientDetails(clientDetailsDTO);
    }

    @ApiOperation("Update client details")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ClientDetailsDTO updateClientDetails(@PathVariable("id") String id,
                                                @Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        return authClientDetailsService.updateClientDetails(id, clientDetailsDTO);
    }

    @ApiOperation("Get client details")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ClientDetailsDTO getClientDetails(@PathVariable("id") String id) {
        return authClientDetailsService.getClientDetails(id);
    }

    @ApiOperation("Delete client details")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteClientDetails(@PathVariable("id") String id) {
        authClientDetailsService.removeClientDetailsById(id);
    }

}
