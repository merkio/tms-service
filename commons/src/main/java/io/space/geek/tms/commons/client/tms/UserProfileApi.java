package io.space.geek.tms.commons.client.tms;

import io.space.geek.tms.commons.dto.tms.UserProfileDTO;
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

@FeignClient(value = "user-profile-api", url = "http://localhost:8181")
@ApiModel(description = "Operations with users profiles")
public interface UserProfileApi {

    @ApiOperation("Get all users profiles")
    @ApiResponse(code = 200, message = "get all users profiles")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/profiles",
        produces = APPLICATION_JSON_VALUE)
    List<UserProfileDTO> getUserProfiles();

    @ApiOperation("Add a new user profile")
    @ApiResponse(code = 200, message = "add a new user profile")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/profiles",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    UserProfileDTO addUserProfile(@Valid @RequestBody UserProfileDTO userProfileDTO);

    @ApiOperation("Update user profile")
    @ApiResponse(code = 200, message = "update user profile")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/profiles/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    UserProfileDTO updateUserProfile(@PathVariable("id") Long id, @Valid @RequestBody UserProfileDTO userProfileDTO);

    @ApiOperation("Get user profile")
    @ApiResponse(code = 200, message = "get user profile")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/profiles/{id}",
        produces = APPLICATION_JSON_VALUE)
    UserProfileDTO getUserProfile(@PathVariable("id") Long id);

    @ApiOperation("Delete user profile")
    @ApiResponse(code = 200, message = "delete user profile")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/tms/profiles/{id}")
    void deleteUserProfile(@PathVariable("id") Long id);

}
