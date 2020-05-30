package io.space.geek.tms.core.controller;

import io.space.geek.tms.commons.client.tms.AcceptanceCriteriaApi;
import io.space.geek.tms.commons.dto.tms.AcceptanceCriteriaDTO;
import io.space.geek.tms.core.service.AcceptanceCriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class AcceptanceCriteriaController implements AcceptanceCriteriaApi {

    private final AcceptanceCriteriaService acceptanceCriteriaService;

    @Override
    @ResponseStatus(OK)
    public List<AcceptanceCriteriaDTO> getAllAcceptanceCriteria() {
        return acceptanceCriteriaService.getAllAcceptanceCriteria();
    }

    @Override
    @ResponseStatus(CREATED)
    public AcceptanceCriteriaDTO addAcceptanceCriteria(@Valid @RequestBody AcceptanceCriteriaDTO acceptanceCriteriaDTO) {
        return acceptanceCriteriaService.addAcceptanceCriteria(acceptanceCriteriaDTO);
    }

    @Override
    @ResponseStatus(OK)
    public AcceptanceCriteriaDTO getAcceptanceCriteria(@PathVariable("id") Long id) {
        return acceptanceCriteriaService.getAcceptanceCriteria(id);
    }

    @Override
    @ResponseStatus(OK)
    public AcceptanceCriteriaDTO updateAcceptanceCriteria(@PathVariable("id") Long id,
                                                          @Valid @RequestBody AcceptanceCriteriaDTO acceptanceCriteriaDTO) {
        return acceptanceCriteriaService.updateAcceptanceCriteria(id, acceptanceCriteriaDTO);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public void deleteAcceptanceCriteria(@NotNull @PathVariable("id") Long id) {
        acceptanceCriteriaService.deleteAcceptanceCriteria(id);
    }
}
