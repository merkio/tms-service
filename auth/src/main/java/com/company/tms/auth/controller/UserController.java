package com.company.tms.auth.controller;

import com.company.tms.auth.service.UserService;
import com.company.tms.commons.client.auth.UserApi;
import com.company.tms.commons.dto.auth.SignUpRequestDTO;
import com.company.tms.commons.dto.auth.UserDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @ApiOperation("Get all users")
    @ResponseStatus(OK)
    @Override
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @ApiOperation("Add a user")
    @ResponseStatus(OK)
    @Override
    public UserDTO addUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){
        return userService.addUser(signUpRequestDTO);
    }

    @ApiOperation("Update user")
    @ResponseStatus(OK)
    @Override
    public UserDTO updateUser(@PathVariable("id") String id, @Valid @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @ApiOperation("Get user by id")
    @ResponseStatus(OK)
    @Override
    public UserDTO getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @ApiOperation("Delete user")
    @ResponseStatus(NO_CONTENT)
    @Override
    public void deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
