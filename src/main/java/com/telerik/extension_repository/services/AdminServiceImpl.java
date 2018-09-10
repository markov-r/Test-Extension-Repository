package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.AdminService;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private UserService userService;

    private ExtensionService extensionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(@Lazy UserService userService, @Lazy ExtensionService extensionService) {
        this.userService = userService;
        this.extensionService = extensionService;
    }

    @Override
    public void approveExtension(Long id) {
        extensionService.approve(id);
    }

    @Override
    public void deleteExtension(Long id) {
        this.extensionService.delete(id);
    }


    @Override
    public void disableUser(Long id) {
        this.userService.disableUser(id);
    }


    @Override
    public void editExtension(ExtensionDto extensionDto) {

    }

    @Override
    public void deleteUserById(Long id) {
     this.userService.deleteUserById(id);
    }

    @Override
    public void setFeatured(Long id) {
        extensionService.setFeatured(id);
    }

    @Override
    public void activateUser(Long userId) {
        this.userRepository.activateUser(userId);
    }
}
