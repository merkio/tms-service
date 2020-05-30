package io.space.geek.tms.core.controller;

import io.space.geek.tms.commons.client.tms.FeatureApi;
import io.space.geek.tms.commons.dto.tms.FeatureDTO;
import io.space.geek.tms.core.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class FeatureController implements FeatureApi {

    private final FeatureService featureService;

    @Override
    @ResponseStatus(OK)
    public List<FeatureDTO> getFeatures() {
        return featureService.getAllFeatures();
    }

    @Override
    @ResponseStatus(CREATED)
    public FeatureDTO addFeature(@Valid @RequestBody FeatureDTO featureDTO) {
        return featureService.saveFeature(featureDTO);
    }

    @Override
    @ResponseStatus(OK)
    public FeatureDTO getFeature(@PathVariable("id") Long id) {
        return featureService.getFeature(id);
    }

    @Override
    @ResponseStatus(OK)
    public FeatureDTO updateFeature(@PathVariable("id") Long id, @Valid @RequestBody FeatureDTO featureDTO) {
        return featureService.updateFeature(id, featureDTO);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public void deleteFeature(@NotNull @PathVariable("id") Long id) {
        featureService.deleteFeature(id);
    }
}
