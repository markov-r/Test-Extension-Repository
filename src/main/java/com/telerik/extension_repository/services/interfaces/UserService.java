package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void register(RegisterUserModel registrationModel);
    List<RegisterUserModel> getAll();
    RegisterUserModel getById(Long id);
    void  edit(RegisterUserModel registerUserModel);
    LoggedUser getByUsernameAndPassword(String username, String password);
    LoginUser getByUsername(String username);
    RegisterUserModel getUserByUsername(String username);
    void deleteUserById(Long id);
    //boolean isUsernameAvailable(String username);
    void disableUser(Long id);
    boolean isEnabled(Long id);
    User findByUsername(String username);
    User getCurrentUser();
    boolean exists(Long id);
    List<RegisterUserModel> getAllNonAdminUsers();
    boolean isExistUsername(String username);

}
