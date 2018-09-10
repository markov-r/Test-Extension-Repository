package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.Tag;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.exceptions.StorageFileNotFoundException;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.models.viewModels.tags.TagView;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.StorageService;
import com.telerik.extension_repository.services.interfaces.UserService;
import com.telerik.extension_repository.utils.Messages;
import com.telerik.extension_repository.utils.UserSession;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/extensions")
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/all")
    public String getAllExtensionPage(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllExt();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }


    //WO
    @GetMapping("add")
    public String getAddExtensionPage(Model model) {
        ExtensionDto addExtensionModel = new ExtensionDto();
        model.addAttribute("extension", addExtensionModel);
        model.addAttribute("view", "/extensions/extension-add");
        model.addAttribute("type","Add");
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

    @GetMapping("new")
    public String getNewestExtensionsPage(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllSortedByDate();
        model.addAttribute("type", "New");
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("featured")
    public String getFeaturedExtensions(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllFeatured();
        model.addAttribute("type", "Featured");
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("/popular")
    public String getPopularExtensions(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllSortedByPopularity();
        model.addAttribute("type", "Popular");
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }


    @GetMapping("edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(Model model, @PathVariable Long id) {
        ExtensionDto extension = extensionService.findExtensionById(id);
        model.addAttribute("type", "Edit");
        model.addAttribute("view", "/extensions/extension-edit")
                .addAttribute("extension", extension);
        return "base-layout";
    }


    @PostMapping("edit/{id}")
    public String editExtension(@ModelAttribute ExtensionDto extensionModel, @PathVariable Long id) {
        if (!extensionService.exists(id)) {
            return "redirect:/notFound/404";
        }
        extensionModel.setId(id);
        //this.storageService.delete(extensionModel.getFilename());
        this.storageService.store(extensionModel.getFile());
        this.extensionService.editExtension(extensionModel);
        return "redirect:/extensions/all";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteExtensionPermanently(@PathVariable Long id) {
        this.extensionService.delete(id);
        return "redirect:/extensions/all";
    }

    @PostMapping("deleteFile/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteFile(@PathVariable Long id) {
        ExtensionDto extensionDto = extensionService.findExtensionById(id);
        this.storageService.delete(extensionDto.getFile().getOriginalFilename());
        return "redirect:/extensions/edit{id}";
    }

    @GetMapping("/extension/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteExtension(Model model, @PathVariable Long id){
//        if (!extensionService.exists(id)) {
//            return "redirect:/error/not-found";
//        }

        ExtensionDto extension = extensionService.findExtensionById(id);
        model.addAttribute("type", "Delete");
        model.addAttribute("extension", extension);
//        model.addAttribute("view", "extensions/delete");
        model.addAttribute("view", "extensions/extension-delete");
        return "base-layout";
    }

    @PostMapping("/extension/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteExtension(@PathVariable Long id){
//        if (!extensionService.exists(id)) {
//            return "redirect:/error/not-found";
//        }

        ExtensionDto extension = extensionService.findExtensionById(id);

//        storageService.delete(extension.getFile().getOriginalFilename());
        extensionService.delete(id);

        return "redirect:/extensions/all";
    }

    private boolean isUserOwnerOrAdmin(Extension extension) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = userService.findByUsername(principal.getUsername());

        return user.isAdmin() || user.isAuthor(extension);
    }
}
