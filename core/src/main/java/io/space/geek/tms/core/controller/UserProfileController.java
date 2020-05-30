package io.space.geek.tms.core.controller;

import io.space.geek.tms.commons.client.tms.UserProfileApi;
import io.space.geek.tms.commons.dto.tms.UserProfileDTO;
import io.space.geek.tms.core.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class UserProfileController implements UserProfileApi {

    private final UserProfileService userProfileService;

    @Override
    @ResponseStatus(OK)
    @ApiOperation("Get all user profiles")
    public List<UserProfileDTO> getUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @Override
    @ResponseStatus(CREATED)
    @ApiOperation("Add user profile")
    public UserProfileDTO addUserProfile(@Valid @RequestBody UserProfileDTO userProfileDTO) {
        return userProfileService.createUserProfile(userProfileDTO);
    }

    @Override
    @ResponseStatus(OK)
    @ApiOperation("Update user profile")
    public UserProfileDTO updateUserProfile(@PathVariable("id") Long id, @Valid @RequestBody UserProfileDTO userProfileDTO) {
        return userProfileService.updateUserProfile(userProfileDTO);
    }

    @Override
    @ResponseStatus(OK)
    @ApiOperation("Get user profile")
    public UserProfileDTO getUserProfile(@PathVariable("id") Long id) {
        return userProfileService.getUserProfile(id);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete user profile")
    public void deleteUserProfile(@PathVariable("id") Long id) {
        userProfileService.deleteUserProfile(id);
    }
}
