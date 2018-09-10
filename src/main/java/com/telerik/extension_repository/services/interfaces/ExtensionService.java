package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;

import java.util.List;

public interface ExtensionService {

    void persist(ExtensionDto addExtensionModel);
    ExtensionDto getById(Long id);
    void update(ExtensionDto extensionModel);
    void approve(Long id);
    List<ExtensionDto> findExtensionsByOwner(String username);
    ExtensionDto getByIdToDetailsPage(Long id);
    List<ExtensionDto> getAllByName(String name);
    List<ExtensionDto> getAllJsons();
    void delete(Long id);
    List<ExtensionDto> getAllPending();
    List<ExtensionDto> getAllFeatured();
    List<ExtensionDto> getAllSortedByDate();
    List<ExtensionDto> getAllExt();
    String findFilename(Long id);
    void setFeatured(Long id);
    void removeFeatured(Long id);
    void incrementDownloadsCount(Long id);
    boolean exists(Long id);
    List<ExtensionDto> getAllSortedByPopularity();
    ExtensionDto findExtensionById(Long id);
    void updateLink(String newLink, Long id);
    void updateFilename(String filename, Long id);
    List<ExtensionDto> filter(String name, String sortBy);
    void editExtension(ExtensionDto addExtensionModel);
    List<ExtensionDto> getAllMatchingNameOrderByName(String keyword);
    boolean isOwnExtension(String username);

}
