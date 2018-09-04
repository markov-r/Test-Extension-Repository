package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;

import java.util.List;

public interface ExtensionService {

    void persist(ExtensionDto addExtensionModel);
    ExtensionDto getByIdToEdit(Long id);
    ExtensionStatusView getById(Long id);
    void update(ExtensionStatusView extensionModel);
    void approve(Long id);
    List<ExtensionModelView> getAll();
    List<Extension> getAllExtensions();
    ExtensionDto getByIdToDetailsPage(Long id);
    List<ExtensionModelView> getAllByName(String name);
    List<ExtensionDto> getAllJsons();
    void delete(Long id);
    List<ExtensionDto> getAllPending();
    List<ExtensionDto> getAllFeatured();
    List<ExtensionDto> getAllSortedByDate();
    List<ExtensionDto> getAllExt();
    String findFilename(Long id);
//    void incrementDownloadsCount(ExtensionDto extensionDetailsView);
    void incrementDownloadsCount(Long id);

    List<ExtensionDto> getAllSortedByPopularity();


//    Blob downloadFile(Long id) throws IOException, SQLException;
//    Extension downloadFileAsExtension(Long id);

}
