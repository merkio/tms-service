package io.space.geek.tms.commons.client.tms;

import io.space.geek.tms.commons.dto.tms.FeatureDTO;
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

@FeignClient(value = "feature-api", url = "http://localhost:8181")
@ApiModel(description = "Operations with features")
public interface FeatureApi {

    @ApiOperation("Get all features")
    @ApiResponse(code = 200, message = "get all features")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/features",
        produces = APPLICATION_JSON_VALUE)
    List<FeatureDTO> getFeatures();

    @ApiOperation("Add a new feature")
    @ApiResponse(code = 200, message = "add a new feature")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/features",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    FeatureDTO addFeature(@Valid @RequestBody FeatureDTO featureDTO);

    @ApiOperation("Update feature")
    @ApiResponse(code = 200, message = "update feature")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/features/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    FeatureDTO updateFeature(@PathVariable("id") Long id, @Valid @RequestBody FeatureDTO featureDTO);

    @ApiOperation("Get feature")
    @ApiResponse(code = 200, message = "get feature")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/features/{id}",
        produces = APPLICATION_JSON_VALUE)
    FeatureDTO getFeature(@PathVariable("id") Long id);

    @ApiOperation("Delete feature")
    @ApiResponse(code = 200, message = "delete feature")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/tms/features/{id}")
    void deleteFeature(@PathVariable("id") Long id);
}
