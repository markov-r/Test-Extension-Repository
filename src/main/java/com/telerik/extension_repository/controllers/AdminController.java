package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.entities.Properties;
import com.telerik.extension_repository.exceptions.UserNotFoundException;
import com.telerik.extension_repository.models.PropertiesDto;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.services.interfaces.AdminService;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.PropertiesService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private PropertiesService propertiesService;


    @GetMapping("extensions")
    public String getAdminPage(Model model){
        List<ExtensionDto> extensionViews = this.extensionService.getAll();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view","/extensions/extensions-table");
        return "base-layout";
    }


    @GetMapping("pending")
    public String getPendingExtensions(Model model){
        List<ExtensionDto> extensionViews = this.extensionService.getAllPending();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view","/admin/admin-pending-extensions");
        return "base-layout";
    }

    @GetMapping("pending/edit/{id}")
    public String getEditPage(Model model, @PathVariable Long id){
        ExtensionDto extensionStatusView = this.extensionService.getById(id);
        model.addAttribute("type","Edit");
        model.addAttribute("view","/admin/admin-extensions-modifiable");
        model.addAttribute("extension", extensionStatusView);
        return "base-layout";
    }

    @PostMapping("pending/edit/{id}")
    public String editExtension(@Valid @ModelAttribute("extensionStatusView") ExtensionDto extensionStatusView, @PathVariable Long id){
        extensionStatusView.setId(id);
        this.extensionService.update(extensionStatusView);
        return "redirect:/admin/pending";
    }

//    @GetMapping("pending/approve/{id}")
//    public String getApproveExtensionPage(Model model,@PathVariable Long id){
//        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
//        model.addAttribute("type","Approve");
//        model.addAttribute("view","/admin/admin-extensions-modifiable");
//        model.addAttribute("extension", extensionStatusView);
//        return "base-layout";
//    }

    @GetMapping("pending/approve/{id}")
    public String getApproveExtensionPage(Model model,@PathVariable Long id){
        ExtensionDto extensionStatusView = this.extensionService.getById(id);
        this.extensionService.approve(id);
        model.addAttribute("view","/admin/admin-pending-extensions");
        return "base-layout";
    }


    @PostMapping("pending/approve/{id}")
    public String approveExtension(@PathVariable Long id){
        this.extensionService.approve(id);
        return "redirect:/admin/pending";
    }

    @PostMapping("makeFeatured/{id}")
    public String makeFeatured(@PathVariable Long id){
        this.extensionService.setFeatured(id);
        return "redirect:/admin/extensions";
    }

    @PostMapping("removeFeatured/{id}")
    public String removeFeatured(@PathVariable Long id){
        this.extensionService.removeFeatured(id);
        return "redirect:/admin/extensions";
    }

// WO
//    @GetMapping("pending/delete/{id}")
//    public String getDeletePartPage(Model model, @PathVariable Long id){
//        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
//        model.addAttribute("type","Delete");
//        model.addAttribute("view","/admin/admin-extensions-modifiable");
//        model.addAttribute("extension",extensionStatusView);
//        return "base-layout";
//    }

    @PostMapping("pending/delete/{id}")
    public String deleteExtension(@PathVariable Long id, Model model) {
            this.adminService.deleteExtension(id);
        return "redirect:/admin/pending";
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model){
        List<RegisterUserModel> users = this.userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("view","/admin/all-users");
        return "base-layout";
    }

    @GetMapping("/user")
    public String getUserByUsername(Model model, @RequestParam(value = "username", required = true) String username){
        RegisterUserModel userModel = this.userService.getUserByUsername(username);
        model.addAttribute("user", userModel);
        model.addAttribute("view","/admin/all-users");
        return "base-layout";
    }

    @PostMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, Model model) {
        RegisterUserModel user = this.userService.getById(userId);
        if (user != null) {
            this.adminService.deleteUserById(userId);
        }

        return "redirect:/admin/users";
    }

    @PostMapping("/users/disableUser/{userId}")
    public String changeUserAccountAccess(@PathVariable Long userId, Model model) {
        RegisterUserModel user = this.userService.getById(userId);
        if (user != null) {
            this.adminService.disableUser(userId);
        }

        return "redirect:/admin/users";
    }


    @ExceptionHandler(UserNotFoundException.class)
    public String catchUserNotFoundException() {

        return "exceptions/user-not-found";
    }

//    @PostMapping("pending")
//    public String approveExtensions(@Valid @ModelAttribute("addExtensionModel") AddExtensionModel addExtensionModel, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "admin";
//        }
//
//        this.adminService.register(registrationModel);
//
//        return "redirect:/users/login";
//    }
//
//    @GetMapping("all")
//    public String getAllExtensionPage(Model model){
//        List<ExtensionModelView> extensionViews = this.extensionService.getAll();
//        model.addAttribute("extensions", extensionViews);
//        model.addAttribute("view","/extensions/extensions-table");
//        return "base-layout";
//    }

    @GetMapping("/sync-panel")
    public String getSyncPanel(Model model){
        PropertiesDto propertiesDto = this.propertiesService.getProperties();
        model.addAttribute("properties", propertiesDto);
        model.addAttribute("view","/admin/sync-panel");
        return "base-layout";
    }

    @GetMapping("/git-sync-interval")
    public String getUpdateIntervalPage(Model model) {
        PropertiesDto propertiesDto = this.propertiesService.getProperties();
        model.addAttribute("properties", propertiesDto);
        model.addAttribute("view","/admin/git-sync-form");
        return "base-layout";
    }

    @PostMapping("/git-sync-interval")
    public String updateSyncInterval(Model model, PropertiesDto propertiesDto) {
        this.propertiesService.updateInterval(propertiesDto.getUpdateInterval());
        return "redirect:/admin/sync-panel";
    }
}