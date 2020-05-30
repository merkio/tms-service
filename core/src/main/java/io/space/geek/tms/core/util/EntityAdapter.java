package io.space.geek.tms.core.util;

import io.space.geek.tms.commons.dto.tms.*;
import io.space.geek.tms.commons.util.BeanUtils;
import io.space.geek.tms.core.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntityAdapter {

    private final ModelMapper modelMapper;

    @NotNull
    public ProjectDTO toDTO(@NotNull Project project) {
        ProjectDTO projectDTO = ProjectDTO.builder().build();
        BeanUtils.copyNonNullProperties(project, projectDTO);
        return projectDTO;
    }

    @NotNull
    public Project fromDTO(@NotNull ProjectDTO projectDTO) {
        Project project = Project.builder().build();
        BeanUtils.copyNonNullProperties(projectDTO, project);
        return project;
    }

    @NotNull
    public UserProfileDTO toDTO(@NotNull UserProfile userProfile) {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder().build();
        BeanUtils.copyNonNullProperties(userProfile, userProfileDTO);
        return userProfileDTO;
    }

    @NotNull
    public UserProfile fromDTO(@NotNull UserProfileDTO userProfileDTO) {
        UserProfile userProfile = UserProfile.builder().build();
        BeanUtils.copyNonNullProperties(userProfileDTO, userProfile);
        return userProfile;
    }

    @NotNull
    public AcceptanceCriteriaDTO toDTO(@NotNull AcceptanceCriteria acceptanceCriteria) {
        AcceptanceCriteriaDTO acceptanceCriteriaDTO = AcceptanceCriteriaDTO.builder().build();
        BeanUtils.copyNonNullProperties(acceptanceCriteria, acceptanceCriteriaDTO);
        return acceptanceCriteriaDTO;
    }

    @NotNull
    public AcceptanceCriteria fromDTO(@NotNull AcceptanceCriteriaDTO acceptanceCriteriaDTO) {
        AcceptanceCriteria acceptanceCriteria = AcceptanceCriteria.builder().build();
        BeanUtils.copyNonNullProperties(acceptanceCriteriaDTO, acceptanceCriteria);
        return acceptanceCriteria;
    }

    @NotNull
    public FeatureDTO toDTO(@NotNull Feature feature) {
        FeatureDTO featureDTO = FeatureDTO.builder().build();
        modelMapper.map(feature, featureDTO);

        return featureDTO;
    }

    @NotNull
    public Feature fromDTO(@NotNull FeatureDTO featureDTO) {
        Feature feature = Feature.builder().build();
        modelMapper.map(featureDTO, feature);

        return feature;
    }

    @NotNull
    public TestCaseDTO toDTO(@NotNull TestCase testCase) {
        TestCaseDTO testCaseDTO = TestCaseDTO.builder().build();

        BeanUtils.copyNonNullProperties(testCase, testCaseDTO);
        return testCaseDTO;
    }

    @NotNull
    public TestCase fromDTO(@NotNull TestCaseDTO testCaseDTO) {
        TestCase testCase = TestCase.builder().build();

        BeanUtils.copyNonNullProperties(testCaseDTO, testCase);
        return testCase;
    }
}
