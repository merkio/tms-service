package com.company.tms.commons.client.auth;

import com.company.tms.commons.dto.auth.ClientDetailsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "auth-client-details", url = "http://localhost:9191")
@ApiModel(description = "Operations with users")
public interface ClientDetailsApi {

    @ApiOperation("Get all client details")
    @ApiResponse(code = 200, message = "get all client details")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/api/v1/auth/client_details/all",
        produces = APPLICATION_JSON_VALUE)
    List<ClientDetailsDTO> getClientDetails();

    @ApiOperation("Add a client details")
    @ApiResponse(code = 200, message = "add a client details")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/api/v1/auth/client_details",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    ClientDetailsDTO addClientDetails(@Valid @RequestBody ClientDetailsDTO clientDetailsDTO);

    @ApiOperation("Update client details")
    @ApiResponse(code = 200, message = "update client details")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/api/v1/auth/client_details/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    ClientDetailsDTO updateClientDetails(@PathVariable("id") String id, @Valid @RequestBody ClientDetailsDTO clientDetailsDTO);

    @ApiOperation("Get client details")
    @ApiResponse(code = 200, message = "get client details")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/api/v1/auth/client_details/{id}",
        produces = APPLICATION_JSON_VALUE)
    ClientDetailsDTO getClientDetails(@PathVariable("id") String id);

    @ApiOperation("Delete client details")
    @ApiResponse(code = 200, message = "delete client details")
    @ResponseStatus(NO_CONTENT)
    @PostMapping(
        value = "/api/v1/auth/client_details/{id}")
    void deleteClientDetails(@PathVariable("id") String id);
}
