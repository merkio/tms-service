package io.space.geek.tms.core.controller;

import io.space.geek.tms.commons.client.tms.ProjectApi;
import io.space.geek.tms.commons.dto.tms.ProjectDTO;
import io.space.geek.tms.core.service.ProjectService;
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
public class ProjectsController implements ProjectApi {

    private final ProjectService projectService;

    @Override
    @ResponseStatus(OK)
    public List<ProjectDTO> getProjects() {
        return projectService.getAllProjects();
    }

    @Override
    @ResponseStatus(OK)
    public ProjectDTO getProject(@PathVariable("id") Long id) {
        return projectService.getProject(id);
    }

    @Override
    @ResponseStatus(CREATED)
    public ProjectDTO addProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @Override
    @ResponseStatus(OK)
    public ProjectDTO updateProject(@PathVariable("id") Long id, @Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectDTO);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public void deleteProject(@PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
    }

}
