package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import org.springframework.security.access.prepost.PreAuthorize;

public interface AdminService{

    @PreAuthorize("hasRole('ADMIN')")
    void approveExtension(ExtensionStatusView extensionModel);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteExtension(Long id);

    void disableUser(Long id);

//    void enableUser(UserView userView);
//
//    void disableUser(UserModelView userView);

    void editExtension(ExtensionStatusView extensionStatusView);

    void deleteUserById(Long id);
}
