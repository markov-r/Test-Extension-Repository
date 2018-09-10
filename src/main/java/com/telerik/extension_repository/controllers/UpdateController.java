package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.log.LogView;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.LogService;
import com.telerik.extension_repository.services.interfaces.StorageService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UpdateController {
    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("updateFilename/{id}")
    public String updateFilename(Model model, @PathVariable Long id) {
        ExtensionDto extensionModel = this.extensionService.getById(id);
        String filename = extensionModel.getFilename();
        model.addAttribute("view", "/user/update-filename");
        model.addAttribute("type", "Edit");
        model.addAttribute("extension", extensionModel);
        return "base-layout";
    }

    @PostMapping("updateFilename/{id}")
    public String getEditExtensionPage(ExtensionDto extensionDto, @PathVariable Long id) {
        this.extensionService.updateFilename(extensionDto.getFilename(), id);
        return "redirect:/user/own";
    }


    @GetMapping("update/updateURL/{id}")
    public String getUpdateURLPage(Model model, @PathVariable Long id) {
        ExtensionDto extensionDto = this.extensionService.findExtensionById(id);
        String sourceRepositoryURL = extensionDto.getSource_repository_link();
        model.addAttribute("extension", extensionDto);
        model.addAttribute("view", "/user/update-repo-source");
        return "base-layout";
    }

    @PostMapping("/update/updateURL/{id}")
    public String updateURL(ExtensionDto extensionDto, @PathVariable Long id) {
        this.extensionService.updateLink(extensionDto.getSource_repository_link(), id);
        return "redirect:/user/own";
    }
}
