package io.space.geek.tms.core.service;


import io.space.geek.tms.core.domain.UserProfile;
import io.space.geek.tms.core.repository.UserProfileRepository;
import io.space.geek.tms.core.util.EntityAdapter;
import io.space.geek.tms.commons.dto.tms.UserProfileDTO;
import io.space.geek.tms.commons.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.space.geek.tms.commons.exception.ResourceNotFoundException.newResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final EntityAdapter entityAdapter;

    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        log.info("Creating user profile for username [{}]", userProfileDTO.getUsername());
        UserProfile userProfile = userProfileRepository.save(entityAdapter.fromDTO(userProfileDTO));
        return entityAdapter.toDTO(userProfile);
    }

    public UserProfileDTO getUserProfile(Long id) {
        log.info("Getting user profile [{}]", id);
        UserProfile userProfile = userProfileRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("User profile", "id", id));
        log.info("Successfully got user profile [{}]", id);
        return entityAdapter.toDTO(userProfile);
    }

    public UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO) {
        log.info("Updating user profile [{}]", userProfileDTO);

        UserProfile userProfile = entityAdapter.fromDTO(userProfileDTO);
        UserProfile currentUserProfile = userProfileRepository.findById(userProfile.getId())
            .orElseThrow(newResourceNotFoundException("User profile", "id", userProfileDTO.getId()));

        BeanUtils.copyNonNullProperties(userProfile, currentUserProfile);
        UserProfile updatedUserProfile = userProfileRepository.save(currentUserProfile);
        return entityAdapter.toDTO(updatedUserProfile);
    }

    public List<UserProfileDTO> getAllUserProfiles() {
        log.info("Getting all user profiles");
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return userProfiles.stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public void deleteUserProfile(Long userProfileId) {
        log.info("Deleting user profile [{}]", userProfileId);
        userProfileRepository.deleteById(userProfileId);
        log.trace("Successfully deleted user profile [{}]", userProfileId);
    }
}
