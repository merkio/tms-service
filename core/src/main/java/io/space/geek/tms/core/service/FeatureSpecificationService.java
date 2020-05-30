package io.space.geek.tms.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeatureSpecificationService {

//    private FileStorageService storageService;
//    private ConfluenceService confluenceService;
//
//    private static final String FEATURE_SPEC_FOLDER = "feature_docs/%s";
//
//    public FeatureSpecificationService(FileStorageService storageService,
//                                       ConfluenceService confluenceService) {
//        this.storageService = storageService;
//        this.confluenceService = confluenceService;
//    }
//
//    public FeatureDTO uploadFeatureSpec(@Nonnull MultipartFile file, @Nonnull FeatureDTO feature, FeatureSpecificationType type) {
//        log.info("Upload feature specification for featureId [{}]", feature.getId());
//
//        String fileName = getFeatureSpecFileName(feature.getName(), type);
//
//        FeatureSpecificationDTO specification = updateOrCreateFeatureSpecificationDTO(feature, type);
//        String filePath = storageService.uploadFile(file, String.format(FEATURE_SPEC_FOLDER, feature.getId()), fileName);
//        specification.setSource(LOCAL);
//        specification.setContentId(null);
//        specification.setVersion(0);
//        specification.setUrl(filePath);
//
//        feature.getSpecifications().add(specification);
//        log.info("Successfully upload new feature specification for feature [{}]", feature.getId());
//        return feature;
//    }
//
//    public FeatureDTO importFeatureSpec(FeatureDTO feature, String contentId, FeatureSpecificationSource source, FeatureSpecificationType type) {
//
//        if (source == CONFLUENCE) {
//            ContentResponse contentResponse = confluenceService.getPageContent(contentId, "body.view,version");
//            String content = contentResponse.getBody().getView().getValue();
//            Integer version = contentResponse.getVersion().getNumber();
//            String folder = getFeatureSpecFolder(feature.getId());
//            String fileName = getFeatureSpecFileName(feature.getName(), type);
//
//            FeatureSpecificationDTO specification = updateOrCreateFeatureSpecificationDTO(feature, type);
//            String url = storageService.uploadStringToFile(content, folder, fileName);
//            specification.setContentId(contentId);
//            specification.setVersion(version);
//            specification.setSource(source);
//            specification.setUrl(url);
//
//            feature.getSpecifications().add(specification);
//        }
//
//        return feature;
//    }
//
//    public FeatureSpecStatusResponse checkFeatureSpecVersion(@Nonnull FeatureDTO feature, @Nonnull FeatureSpecificationType type) {
//        log.info("Checking feature specification version with type [{}] for feature [{}]", type, feature.getId());
//
//        Optional<FeatureSpecificationDTO> specificationO = findFeatureSpecByType(feature, type);
//
//        if (!specificationO.isPresent()) {
//            log.error("Feature specification with type [{}] for feature [{}] doesn't exist", type, feature.getId());
//            throw new NotFoundException(
//                String.format("Feature specification with type [%s] for feature [%s] doesn't exist", type, feature.getId()));
//        }
//        FeatureSpecificationDTO specification = specificationO.get();
//        Integer actualVersion;
//        switch (specification.getSource()) {
//            case CONFLUENCE:
//                actualVersion = confluenceService.getPageContent(specification.getContentId(), "version").getVersion().getNumber();
//                break;
//            default:
//                actualVersion = 0;
//        }
//        Integer savedVersion = specification.getVersion();
//        Boolean status = Boolean.TRUE;
//        if (savedVersion < actualVersion) {
//            status = Boolean.FALSE;
//        }
//        return new FeatureSpecStatusResponse(status, savedVersion, actualVersion);
//    }
//
//    public void deleteAllFeatureSpecsForFeature(@Nonnull Long featureId) {
//        log.info("Deleting all feature specifications from S3 for feature [{}]", featureId);
//        storageService.deleteObject(String.format(FEATURE_SPEC_FOLDER, featureId) + "/");
//        log.info("Successfully deleted all feature specifications from S3 for feature [{}]", featureId);
//    }
//
//    private FeatureSpecificationDTO updateOrCreateFeatureSpecificationDTO(FeatureDTO feature,
//                                                                          FeatureSpecificationType type) {
//        Optional<FeatureSpecificationDTO> specificationO = findFeatureSpecByType(feature, type);
//        FeatureSpecificationDTO updatedSpecification = FeatureSpecificationDTO.builder()
//            .version(0)
//            .source(LOCAL)
//            .type(type)
//            .build();
//
//        if (specificationO.isPresent()) {
//            updatedSpecification = specificationO.get();
//            feature.getSpecifications().remove(updatedSpecification);
//            storageService.deleteObject(updatedSpecification.getUrl());
//            updatedSpecification.setType(type);
//        }
//        return updatedSpecification;
//    }
//
//    private String getFeatureSpecFileName(String featureName, FeatureSpecificationType specificationType) {
//        return String.format("%s_%s", featureName.replaceAll(" ", "_"), specificationType).toLowerCase();
//    }
//
//    private String getFeatureSpecFolder(Long featureId) {
//        return String.format(FEATURE_SPEC_FOLDER, featureId);
//    }
//
//    private Optional<FeatureSpecificationDTO> findFeatureSpecByType(FeatureDTO feature, FeatureSpecificationType type) {
//        return feature.getSpecifications().stream()
//            .filter(spec -> spec.getType() == type)
//            .findFirst();
//    }
//
//    public FeatureDTO deleteFeatureSpecification(@Nonnull FeatureDTO feature, FeatureSpecificationType type) {
//        log.info("Deleting feature specification for feature [{}] with type [{}]", feature.getId(), type);
//
//        Optional<FeatureSpecificationDTO> featureSpecO = findFeatureSpecByType(feature, type);
//        if (featureSpecO.isPresent()) {
//            FeatureSpecificationDTO spec = featureSpecO.get();
//            storageService.deleteObject(spec.getUrl());
//            feature.getSpecifications().remove(spec);
//            log.info("Feature specification [{}] successfully deleted!", spec.getId());
//            return feature;
//        }
//        log.info("Feature specification for feature [{}] and type [{}] didn't found", feature.getId(), type);
//        return feature;
//    }
}
