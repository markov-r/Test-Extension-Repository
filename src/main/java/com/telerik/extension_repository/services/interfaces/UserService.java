package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.models.bindingModels.user.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(EditUserModel registationModel);
    List<EditUserModel> getAll();
    EditUserModel getById(Long id);
    void  edit(EditUserModel editUserModel);
    LoggedUser getByUsernameAndPassword(String username, String password);
    LoginUser getByUsername(String username);
    EditUserModel getUserByUsername(String username);
    void deleteUserById(Long id);
    boolean isUsernameAvailable(String username);
    void disableUser(Long id);
    boolean isEnabled(Long id);
    List<ExtensionStatusView> getOwnsExtensions(Long id);
    User findByUsername(String username);

}
