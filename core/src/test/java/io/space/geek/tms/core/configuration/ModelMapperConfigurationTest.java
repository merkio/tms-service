package io.space.geek.tms.core.configuration;


import io.space.geek.tms.commons.config.ModelMapperConfiguration;
import io.space.geek.tms.commons.constant.tms.ProjectType;
import io.space.geek.tms.commons.dto.tms.ProjectDTO;
import io.space.geek.tms.core.domain.Project;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = ModelMapperConfiguration.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class ModelMapperConfigurationTest {

    private final ModelMapper modelMapper;

    @Test
    void convertUserToUserDTO() {
        Project project = Project.builder()
            .name("Project")
            .description("New Project")
            .type(ProjectType.APPLICATION)
            .build();

        ProjectDTO projectDTO = ProjectDTO.builder().build();
        modelMapper.map(project, projectDTO);
        System.out.println(projectDTO);
        Assertions.assertNotNull(projectDTO.getName());
    }
}
