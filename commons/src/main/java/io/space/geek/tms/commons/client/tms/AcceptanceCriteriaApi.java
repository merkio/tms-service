package io.space.geek.tms.commons.client.tms;

import io.space.geek.tms.commons.dto.tms.AcceptanceCriteriaDTO;
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

@FeignClient(value = "acceptance-criteria-api", url = "http://localhost:8181")
@ApiModel(description = "Operations with acceptance criteria")
public interface AcceptanceCriteriaApi {

    @ApiOperation("Get all acceptance criteria")
    @ApiResponse(code = 200, message = "get all acceptance criteria")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/acceptance_criteria",
        produces = APPLICATION_JSON_VALUE)
    List<AcceptanceCriteriaDTO> getAllAcceptanceCriteria();

    @ApiOperation("Add a new acceptance criteria")
    @ApiResponse(code = 200, message = "add a new acceptance criteria")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/acceptance_criteria",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    AcceptanceCriteriaDTO addAcceptanceCriteria(@Valid @RequestBody AcceptanceCriteriaDTO criteriaDTO);

    @ApiOperation("Update acceptance criteria")
    @ApiResponse(code = 200, message = "update acceptance criteria")
    @ResponseStatus(OK)
    @PostMapping(
        value = "/tms/acceptance_criteria/{id}",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    AcceptanceCriteriaDTO updateAcceptanceCriteria(@PathVariable("id") Long id,
                                                   @Valid @RequestBody AcceptanceCriteriaDTO criteriaDTO);

    @ApiOperation("Get acceptance criteria")
    @ApiResponse(code = 200, message = "get acceptance criteria")
    @ResponseStatus(OK)
    @GetMapping(
        value = "/tms/acceptance_criteria/{id}",
        produces = APPLICATION_JSON_VALUE)
    AcceptanceCriteriaDTO getAcceptanceCriteria(@PathVariable("id") Long id);

    @ApiOperation("Delete acceptance criteria")
    @ApiResponse(code = 200, message = "delete acceptance criteria")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        value = "/tms/acceptance_criteria/{id}")
    void deleteAcceptanceCriteria(@PathVariable("id") Long id);
}
