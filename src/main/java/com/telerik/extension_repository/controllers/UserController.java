package com.telerik.extension_repository.controllers;

import com.sun.javafx.geom.Path2D;
import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.PropertiesDto;
import com.telerik.extension_repository.models.bindingModels.user.*;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.AuthorityService;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.UserService;
import com.telerik.extension_repository.utils.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ExtensionService extensionService;

    @Autowired
    private AuthorityService roleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("user/own")
    public String getOwnExtensionPage(Model model) {
        String ownerName = null;
        try {
            ownerName = UserSession.getCurrentUser().getUsername();
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
        }
        List<ExtensionDto> extensionViews = this.extensionService.findExtensionsByOwner(ownerName);
        model.addAttribute("type", "Own");
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserModel registerUserModel, Model model){
        model.addAttribute("type", "Register");
        model.addAttribute("view","user/register-user");
        return "base-layout";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUserModel registerUserModel, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("type", "Register");
            model.addAttribute("view","user/register-user");
            return "base-layout";
        }
        this.userService.register(registerUserModel);

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false)String error, Model model){
        if (error != null){
            model.addAttribute("error","Wrong username or password");
        }

        model.addAttribute("view","user/login-user");
        return "base-layout";
    }


    @GetMapping("user/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) {
        UserDetails userDetails = UserSession.getCurrentUser();
        model.addAttribute("user", userDetails);
        model.addAttribute("view", "user/profile");
        return "base-layout";
    }

    private List<AuthorityModel> getRoles(){
        return this.roleService.getAll();
    }

    @GetMapping("user/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    private String getEditUserPage(Model model, RegisterUserModel registerUserModel, Long id){
        UserDetails userDetails = UserSession.getCurrentUser();
        model.addAttribute("user", userDetails);
        model.addAttribute("type", "Edit");
        model.addAttribute("view","user/edit-user");
        return "base-layout";
    }

    @PostMapping("user/edit/{id}")
    public String  editUser(@PathVariable Long id){
        RegisterUserModel registerUserModel = this.userService.getById(id);
        this.userService.edit(registerUserModel);
        return "redirect:/user/profile";
    }


    private User getCurrentUser() {

        if (!(SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken)) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            return this.userRepository.findOneByUsername(principal.getUsername());

        }
        return null;
    }

}