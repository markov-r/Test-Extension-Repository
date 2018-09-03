package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;

import java.util.List;

public interface ExtensionService {

    void persist(ExtensionDetailsView addExtensionModel);
    ExtensionDetailsView getByIdToEdit(Long id);
    ExtensionStatusView getById(Long id);
    void update(ExtensionStatusView extensionModel);
    void approve(Long id);
    List<ExtensionModelView> getAll();
    List<Extension> getAllExtensions();
    ExtensionDetailsView getByIdToDetailsPage(Long id);
    List<ExtensionModelView> getAllByName(String name);
    List<ExtensionDetailsView> getAllJsons();
    void delete(Long id);
    List<ExtensionDetailsView> getAllPending();
    List<ExtensionDetailsView> getAllFeatured();
    List<ExtensionDetailsView> getAllSortedByDate();
    List<ExtensionDetailsView> getAllExt();
    String findFilename(Long id);
//    void incrementDownloadsCount(ExtensionDetailsView extensionDetailsView);
    void incrementDownloadsCount(Long id);

    List<ExtensionDetailsView> getAllSortedByPopularity();


//    Blob downloadFile(Long id) throws IOException, SQLException;
//    Extension downloadFileAsExtension(Long id);

}
