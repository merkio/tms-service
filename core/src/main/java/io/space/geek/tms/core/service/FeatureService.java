package io.space.geek.tms.core.service;

import io.space.geek.tms.commons.dto.tms.FeatureDTO;
import io.space.geek.tms.commons.util.BeanUtils;
import io.space.geek.tms.core.domain.Feature;
import io.space.geek.tms.core.repository.FeatureRepository;
import io.space.geek.tms.core.util.EntityAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.space.geek.tms.commons.exception.ResourceNotFoundException.newResourceNotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final AcceptanceCriteriaService acceptanceCriteriaService;
    private final EntityAdapter entityAdapter;

    public FeatureDTO saveFeature(FeatureDTO featureDTO) {
        log.info("Save feature [{}] to features", featureDTO.getName());

        Feature feature = entityAdapter.fromDTO(featureDTO);

        Feature savedFeature = featureRepository.saveAndFlush(feature);
        log.trace("Successfully saved feature: [{}] with name [{}]", savedFeature.getId(), savedFeature.getName());
        return entityAdapter.toDTO(savedFeature);
    }

    public FeatureDTO updateFeature(Long id, FeatureDTO featureDTO) {
        log.info("Updating feature item [{}]", id);
        Feature feature = entityAdapter.fromDTO(featureDTO);

        Feature currentFeature = featureRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("Feature", "id", id));

        BeanUtils.copyNonNullProperties(feature, currentFeature);
        Feature updatedFeature = featureRepository.saveAndFlush(currentFeature);
        log.info("Successfully updated feature item: {}", updatedFeature.getId());

        return entityAdapter.toDTO(updatedFeature);
    }

    public void deleteFeature(Long id) {
        log.info("Deleting feature with id [{}]", id);

        log.info("Delete acceptance criteria with feature_id [{}]", id);
        acceptanceCriteriaService.deleteAcceptanceCriteriaWithFeatureId(id);
        log.info("Successfully deleted acceptance criteria with feature_id [{}]", id);

        log.info("Delete all feature specifications on S3 with feature_id [{}]", id);
        Feature feature = featureRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("Feature", "id", id));
        if (CollectionUtils.isEmpty(feature.getSpecifications())) {
            log.info("Successfully deleted all feature specifications on S3 with feature_id [{}]", id);
        } else {
            log.info("Nothing to delete for feature [{}]", id);
        }

        featureRepository.deleteById(id);
        log.info("Successfully removed feature with id [{}]", id);
    }

    public FeatureDTO getFeature(Long id) {
        Feature feature = featureRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("Feature", "id", id));
        return entityAdapter.toDTO(feature);
    }

    public List<FeatureDTO> getAllFeatures() {
        return featureRepository.findAll().stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }
}
