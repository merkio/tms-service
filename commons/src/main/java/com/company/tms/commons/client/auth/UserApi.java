package com.company.tms.commons.client.auth;

import com.company.tms.commons.dto.auth.SignUpRequestDTO;
import com.company.tms.commons.dto.auth.UserDTO;
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

@FeignClient(value = "auth-users", url = "http://localhost:9191")
@ApiModel(description = "Operations with users")
public interface UserApi {

    @ApiOperation("Get all users")
    @ApiResponse(code = 200, message = "get all users")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/api/v1/auth/users",
        produces = APPLICATION_JSON_VALUE)
    List<UserDTO> getUsers();

    @ApiOperation("Add a new user")
    @ApiResponse(code = 200, message = "add a new user")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/api/v1/auth/user",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    UserDTO addUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO);

    @ApiOperation("Update user")
    @ApiResponse(code = 200, message = "update user")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/api/v1/auth/user/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    UserDTO updateUser(@PathVariable("id") String id, @Valid @RequestBody UserDTO userDTO);

    @ApiOperation("Get user")
    @ApiResponse(code = 200, message = "get user")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/api/v1/auth/user/{id}",
        produces = APPLICATION_JSON_VALUE)
    UserDTO getUser(@PathVariable("id") String id);

    @ApiOperation("Delete user")
    @ApiResponse(code = 200, message = "delete user")
    @ResponseStatus(NO_CONTENT)
    @PostMapping(
        value = "/api/v1/auth/user/{id}")
    void deleteUser(@PathVariable("id") String id);
}
