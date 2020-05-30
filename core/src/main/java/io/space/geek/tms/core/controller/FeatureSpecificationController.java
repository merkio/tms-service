package io.space.geek.tms.core.controller;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeatureSpecificationController {

//    private FeatureSpecificationService featureSpecService;
//    private FeatureService featureService;
//
//    public FeatureSpecificationController(FeatureService featureService,
//                                          FeatureSpecificationService featureSpecService) {
//        this.featureSpecService = featureSpecService;
//        this.featureService = featureService;
//    }
//
//    @Override
//    @ResponseStatus(CREATED)
//    @ApiOperation("Upload feature specification to S3")
//    public FeatureDTO uploadFeatureSpecification(@RequestPart(value = "file") MultipartFile file,
//                                                 @PathVariable("featureId") Long featureId,
//                                                 @RequestParam("type") FeatureSpecificationType type) {
//        return featureService.saveFeature(featureSpecService.uploadFeatureSpec(file, featureService.getFeature(featureId), type));
//    }
//
//    @Override
//    @ResponseStatus(OK)
//    @ApiOperation("Import feature specification to S3")
//    public FeatureDTO importFeatureSpecification(@PathVariable("featureId") Long featureId,
//                                                 @RequestParam("contentId") String contentId,
//                                                 @RequestParam("source") FeatureSpecificationSource source,
//                                                 @RequestParam("type") FeatureSpecificationType type) {
//        return featureService.saveFeature(featureSpecService.importFeatureSpec(featureService.getFeature(featureId), contentId, source, type));
//    }
//
//    @Override
//    @ResponseStatus(OK)
//    @ApiOperation("Check status feature specification")
//    public FeatureSpecStatusResponse checkStatusSpecification(@PathVariable("featureId") Long featureId,
//                                                              @RequestParam("type") FeatureSpecificationType type) {
//        return featureSpecService.checkFeatureSpecVersion(featureService.getFeature(featureId), type);
//    }
//
//    @ApiOperation("Delete feature specification by featureId and feature spec type")
//    @ResponseStatus(NO_CONTENT)
//    @Override
//    public void deleteFeatureSpecification(@NotNull @PathVariable("featureId") Long featureId,
//                                           @NotNull @RequestParam("type") FeatureSpecificationType type) {
//        featureService.saveFeature(featureSpecService.deleteFeatureSpecification(featureService.getFeature(featureId), type));
//    }
}
