package io.space.geek.tms.core.service;


import io.space.geek.tms.commons.dto.tms.AcceptanceCriteriaDTO;
import io.space.geek.tms.commons.util.BeanUtils;
import io.space.geek.tms.core.domain.AcceptanceCriteria;
import io.space.geek.tms.core.repository.AcceptanceCriteriaRepository;
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
public class AcceptanceCriteriaService {

    private final AcceptanceCriteriaRepository acceptanceCriteriaRepository;
    private final TestCaseService testCaseService;
    private final EntityAdapter entityAdapter;

    public AcceptanceCriteriaDTO addAcceptanceCriteria(AcceptanceCriteriaDTO acceptanceCriteriaDTO) {
        log.info("Add acceptance criteria [{}]", acceptanceCriteriaDTO.getName());

        AcceptanceCriteria acceptanceCriteria = entityAdapter.fromDTO(acceptanceCriteriaDTO);

        AcceptanceCriteria acceptCriteria = acceptanceCriteriaRepository.saveAndFlush(acceptanceCriteria);
        log.trace("Successfully added to acceptance criteria: [{}]", acceptCriteria.getId());
        return entityAdapter.toDTO(acceptCriteria);
    }

    public AcceptanceCriteriaDTO updateAcceptanceCriteria(Long id, AcceptanceCriteriaDTO acceptanceCriteriaDTO) {
        log.info("Updating acceptance criteria item [{}]", id);
        AcceptanceCriteria acceptanceCriteria = entityAdapter.fromDTO(acceptanceCriteriaDTO);

        AcceptanceCriteria currentAcceptanceCriteria = this.acceptanceCriteriaRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("Acceptance criteria", "id", id));
        BeanUtils.copyNonNullProperties(acceptanceCriteria, currentAcceptanceCriteria);

        final AcceptanceCriteria updatedAcceptanceCriteria = this.acceptanceCriteriaRepository.saveAndFlush(currentAcceptanceCriteria);
        log.info("Successfully updated acceptance criteria item: {}", updatedAcceptanceCriteria.getId());

        return entityAdapter.toDTO(updatedAcceptanceCriteria);
    }

    public void deleteAcceptanceCriteria(Long id) {
        log.info("Deleting acceptance criteria with id [{}]", id);
//        testStoryService.deleteTestStoriesWithAcceptanceCriteriaId(id);
        this.acceptanceCriteriaRepository.deleteById(id);
        log.info("Successfully removed acceptance criteria with id [{}]", id);
    }

    public AcceptanceCriteriaDTO getAcceptanceCriteria(Long id) {
        log.info("Get acceptance criteria with id [{}]", id);
        AcceptanceCriteria acceptanceCriteria = acceptanceCriteriaRepository.findById(id)
            .orElseThrow(newResourceNotFoundException("Acceptance criteria", "id", id));
        return entityAdapter.toDTO(acceptanceCriteria);
    }

    public List<AcceptanceCriteriaDTO> updateAcceptanceCriteriaBatch(List<AcceptanceCriteriaDTO> acceptanceCriteriaBatch) {
        log.info("Updating [{}] acceptance criteria", acceptanceCriteriaBatch.size());
        List<AcceptanceCriteriaDTO> updatedAcceptanceCriteriaBatch = acceptanceCriteriaBatch.stream()
            .map(acceptanceCriteriaDTO -> updateAcceptanceCriteria(acceptanceCriteriaDTO.getId(), acceptanceCriteriaDTO))
            .collect(Collectors.toList());
        log.info("Successfully updated [{}] acceptance criteria", updatedAcceptanceCriteriaBatch.size());
        return updatedAcceptanceCriteriaBatch;
    }

    public void deleteAcceptanceCriteriaWithFeatureId(Long featureId) {
        log.info("Deleting all acceptance criteria with featureId [{}]", featureId);
        List<AcceptanceCriteria> acceptanceCriteriaList = acceptanceCriteriaRepository.findAllByFeatureId(featureId);
        acceptanceCriteriaList.forEach(ac -> {
//            testStoryService.deleteTestStoriesWithAcceptanceCriteriaId(ac.getId());
            deleteAcceptanceCriteria(ac.getId());
        });
    }

    public List<AcceptanceCriteria> findAllByFeatureId(Long featureId) {
        log.info("Get all acceptance criteria by featureId [{}]", featureId);
        return acceptanceCriteriaRepository.findAllByFeatureId(featureId);
    }

    public List<AcceptanceCriteriaDTO> getAllAcceptanceCriteria() {
        return acceptanceCriteriaRepository.findAll().stream()
            .map(entityAdapter::toDTO)
            .collect(Collectors.toList());
    }
}
