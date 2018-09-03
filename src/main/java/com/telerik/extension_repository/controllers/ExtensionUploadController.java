//package com.telerik.extension_repository.controllers;
//
//
//import com.telerik.extension_repository.models.ExtensionDetailsView;
//import com.telerik.extension_repository.services.interfaces.ExtensionService;
//import com.telerik.extension_repository.services.interfaces.FileStorageService;
//import com.telerik.extension_repository.services.interfaces.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ExtensionUploadController {
//
//    private ExtensionService extensionService;
//
////    private UserService userService;
////
////    private FileStorageService fileStorageService;
//
//    @Autowired
//    public ExtensionUploadController(ExtensionService extensionService) {
//        this.extensionService = extensionService;
//    }
//
//
//
//    @GetMapping("extensions/add")
//    public String showForm(Model model) {
//        model.addAttribute("view", "extension/extensions-add");
//
//        return "base-layout";
//    }
//
//    @PostMapping("extensions/add")
//    public String upload(@ModelAttribute ExtensionDetailsView extensionDto, Model model) {
//        try {
//            this.extensionService.persist(extensionDto);
//            model.addAttribute("message", "File uploaded successfully! -> filename = " + extensionDto.getFile().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Fail! -> uploaded filename: " + extensionDto.getFile().toString());
//        }
//        return "redirect:/extensions/all";
//    }
//
//    // download
//
//}
