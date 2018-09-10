package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import org.springframework.security.access.prepost.PreAuthorize;

public interface AdminService{

    @PreAuthorize("hasRole('ADMIN')")
    void approveExtension(Long id);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteExtension(Long id);

    void disableUser(Long id);

    void editExtension(ExtensionDto extensionStatusView);

    void deleteUserById(Long id);

    void setFeatured(Long id);

    void activateUser(Long userId);
}
