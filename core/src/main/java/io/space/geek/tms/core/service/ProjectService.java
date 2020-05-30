package io.space.geek.tms.core.service;

import io.space.geek.tms.commons.dto.tms.ProjectDTO;
import io.space.geek.tms.commons.util.BeanUtils;
import io.space.geek.tms.core.domain.Project;
import io.space.geek.tms.core.repository.ProjectRepository;
import io.space.geek.tms.core.util.EntityAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.space.geek.tms.commons.exception.ResourceNotFoundException.newResourceNotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EntityAdapter entityAdapter;

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        log.info("Creating project with name [{}]", projectDTO.getName());
        Project project = projectRepository.save(entityAdapter.fromDTO(projectDTO));
        return entityAdapter.toDTO(project);
    }

    public ProjectDTO getProject(Long id) {
        log.info("Getting project [{}]", id);
        Project project = this.projectRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("Project", "id", id));
        log.info("Successfully got project [{}]", id);
        return entityAdapter.toDTO(project);
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        log.info("Updating project [{}]", projectDTO);

        Project project = entityAdapter.fromDTO(projectDTO);
        Project currentProject = projectRepository.findById(projectDTO.getId())
            .orElseThrow(newResourceNotFoundException("Project", "id", projectDTO.getId()));

        BeanUtils.copyNonNullProperties(project, currentProject);
        final Project updatedProject = this.projectRepository.save(currentProject);
        return entityAdapter.toDTO(updatedProject);
    }

    public List<ProjectDTO> getAllProjects() {
        log.info("Getting all projects");
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public void deleteProject(Long projectId) {
        log.info("Deleting project [{}]", projectId);
        projectRepository.deleteById(projectId);
        log.trace("Successfully deleted project [{}]", projectId);
    }
}
