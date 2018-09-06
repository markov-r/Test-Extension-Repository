package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.exceptions.StorageFileNotFoundException;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/extensions")
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private TagRepository tagRepository;

//WO
    @GetMapping("add")
    public String getAddExtensionPage(Model model) {
        ExtensionDto addExtensionModel = new ExtensionDto();
        model.addAttribute("extension", addExtensionModel);
        model.addAttribute("view", "/extensions/extension-add_old");
//        model.addAttribute("type","Add");
        return "base-layout";
    }

    // WO
    @PostMapping("add")
    public String addExtension(@Valid @ModelAttribute("addExtensionModel") ExtensionDto addExtensionModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
        }
        this.extensionService.persist(addExtensionModel);
        this.storageService.store(addExtensionModel.getFile());
        return "redirect:/";
    }





// get Extension details page WO
//    @GetMapping("{id}")
//    public String getExtensionDetailsPage(Model model, @PathVariable Long id) {
//        ExtensionDto extensionDetailsView = this.extensionService.getByIdToDetailsPage(id);
//        model.addAttribute("extension", extensionDetailsView);
//        model.addAttribute("view", "/extensions/extension-details");
//        return "base-layout";
//    }



    @GetMapping("edit/{id}")
    public String getEditExtensionPage(Model model, @PathVariable Long id) {
        ExtensionDto extensionModel = this.extensionService.getById(id);
        model.addAttribute("view", "/extensions/extension-edit");
        model.addAttribute("type", "Edit");
        model.addAttribute("extension", extensionModel);
        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editExtension(@ModelAttribute ExtensionDto extensionModel, @PathVariable Long id) {
        extensionModel.setId(id);
        this.extensionService.update(extensionModel);
        return "redirect:/extensions/all";
    }

    @GetMapping("delete/{id}")
    public String deletePart(@PathVariable Long id) {
        this.extensionService.delete(id);
        return "redirect:/extensions/all";
    }

}
