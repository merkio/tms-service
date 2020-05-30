package io.space.geek.tms.commons.client.tms;

import io.space.geek.tms.commons.dto.tms.ProjectDTO;
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

@FeignClient(value = "project-api", url = "http://localhost:8181")
@ApiModel(description = "Operations with projects")
public interface ProjectApi {

    @ApiOperation("Get all projects")
    @ApiResponse(code = 200, message = "get all projects")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/projects",
        produces = APPLICATION_JSON_VALUE)
    List<ProjectDTO> getProjects();

    @ApiOperation("Add a new project")
    @ApiResponse(code = 200, message = "add a new project")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/projects",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    ProjectDTO addProject(@Valid @RequestBody ProjectDTO projectDTO);

    @ApiOperation("Update project")
    @ApiResponse(code = 200, message = "update project")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/projects/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    ProjectDTO updateProject(@PathVariable("id") Long id, @Valid @RequestBody ProjectDTO projectDTO);

    @ApiOperation("Get project")
    @ApiResponse(code = 200, message = "get project")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/projects/{id}",
        produces = APPLICATION_JSON_VALUE)
    ProjectDTO getProject(@PathVariable("id") Long id);

    @ApiOperation("Delete project")
    @ApiResponse(code = 200, message = "delete project")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/tms/projects/{id}")
    void deleteProject(@PathVariable("id") Long id);
}
