package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.models.bindingModels.user.*;
import com.telerik.extension_repository.services.UserServiceImpl;
import com.telerik.extension_repository.services.interfaces.AuthorityService;
//import com.telerik.extension_repository.services.interfaces.NotificationService;
import com.telerik.extension_repository.services.interfaces.UserService;
import com.telerik.extension_repository.utils.Messages;
//import com.telerik.extension_repository.utils.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService roleService;

//    @Autowired
//    private NotificationService notificationService;


    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserModel registerUserModel, Model model){
        model.addAttribute("type", "Register");
        model.addAttribute("view","user/register-user");
        return "base-layout";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUserModel registerUserModel, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
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


//    @GetMapping("user/profile")
//    @PreAuthorize("isAuthenticated()")
//    public String profilePage(Model model) {
//        UserDetails userDetails = UserSession.getCurrentUser();
//        RegisterUserModel currUser = this.userService.getUserByUsername(userDetails.getUsername());
//        model.addAttribute("user", currUser);
//        model.addAttribute("view", "user/profile");
//
//        return "base-layout";
//    }

    //@ModelAttribute(name = "roles")
    private List<AuthorityModel> getRoles(){
        return this.roleService.getAll();
    }

//    @GetMapping("user/edit/{id}")
//    @PreAuthorize("isAuthenticated()")
//    private String getEditUserPage(Model model, @PathVariable Long id, @RequestParam(defaultValue = "false") boolean checkbox){
//        if (!this.userService.exists(id)) {
//            this.notificationService.addErrorMessage(Messages.NOT_FOUND);
//            return "redirect:/";
//        }
//        UserDetails userDetails = UserSession.getCurrentUser();
//        RegisterUserModel currUser = this.userService.getUserByUsername(userDetails.getUsername());
////        RegisterUserModel registerUserModel = this.userService.getById(id);
//        model.addAttribute("type", "Edit");
//        model.addAttribute("user", currUser);
//        model.addAttribute("view","register-user");
//        return "base-layout";
//    }

//    @GetMapping("/user/edit/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public String edit(Model model, @PathVariable Long id,  @RequestParam(defaultValue = "false") boolean checkbox ) {
//        if (!this.userService.exists(id)) {
//            this.notificationService.addErrorMessage(Messages.NOT_FOUND);
//            return "redirect:/";
//        }
//
//        RegisterUserModel user = this.userService.getById(id);
////        boolean isAdmin = user.isAdmin();
////        model.addAttribute("isAdmin", isAdmin);
//        model.addAttribute("view", "user/edit")
//                .addAttribute("user", user);
//        return "admin/admin_panel-layout";
//    }

    @PostMapping("user/edit/{id}")
    public String  editUser(Model model, @PathVariable Long id){
        RegisterUserModel registerUserModel = this.userService.getById(id);
        this.userService.edit(registerUserModel);
        return "redirect:/users";
    }
}