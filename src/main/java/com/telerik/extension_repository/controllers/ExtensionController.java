package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.exceptions.StorageFileNotFoundException;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.FileStorageService;
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
    private TagRepository tagRepository;

//WO
    @GetMapping("add")
    public String getAddExtensionPage(Model model) {
        ExtensionDetailsView addExtensionModel = new ExtensionDetailsView();
        model.addAttribute("extension", addExtensionModel);
        model.addAttribute("view", "/extensions/extension-add_old");
//        model.addAttribute("type","Add");
        return "base-layout";
    }

    // WO
    @PostMapping("add")
    public String addExtension(@Valid @ModelAttribute("addExtensionModel") ExtensionDetailsView addExtensionModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
        }
        this.extensionService.persist(addExtensionModel);
        this.storageService.store(addExtensionModel.getFile());
        return "redirect:/extensions/all";
    }



    // WO
    @GetMapping("all")
    public String getAllExtensionPage(Model model) {
        List<ExtensionModelView> extensionViews = this.extensionService.getAll();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("new")
    public String getNewestExtensionsPage(Model model) {
        List<ExtensionDetailsView> extensionViews = this.extensionService.getAllSortedByDate();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table-list-new");
        return "base-layout";
    }

    @GetMapping("featured")
    public String getFeaturedExtensions(Model model) {
        List<ExtensionDetailsView> extensionViews = this.extensionService.getAllFeatured();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("popular")
    public String getPopularExtensions(Model model) {
        List<ExtensionDetailsView> extensionViews = this.extensionService.getAllSortedByPopularity();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }


// get Extension details page WO
//    @GetMapping("{id}")
//    public String getExtensionDetailsPage(Model model, @PathVariable Long id) {
//        ExtensionDetailsView extensionDetailsView = this.extensionService.getByIdToDetailsPage(id);
//        model.addAttribute("extension", extensionDetailsView);
//        model.addAttribute("view", "/extensions/extension-details");
//        return "base-layout";
//    }

// get Extension details page WO
    @GetMapping("{id}")
    public String getExtensionDetailsPage(Model model, @PathVariable Long id) {
        ExtensionDetailsView extensionDetailsView = this.extensionService.getByIdToDetailsPage(id);
        model.addAttribute("extension", extensionDetailsView);
        model.addAttribute("view", "/extensions/details");
        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String getEditExtensionPage(Model model, @PathVariable Long id) {
        ExtensionDetailsView extensionModel = this.extensionService.getByIdToEdit(id);
        model.addAttribute("view", "/extensions/extension-edit");
        model.addAttribute("type", "Edit");
        model.addAttribute("extension", extensionModel);
        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editExtension(@ModelAttribute ExtensionStatusView extensionModel, @PathVariable Long id) {
        extensionModel.setId(id);
        this.extensionService.update(extensionModel);
        return "redirect:/extensions/all";
    }

    @GetMapping("delete/{id}")
    public String deletePart(@PathVariable Long id) {
        this.extensionService.delete(id);
        return "redirect:/extensions/all";
    }

    @GetMapping("add/files")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(ExtensionController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "/extensions/upload-file";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {

        String filename = this.extensionService.findFilename(id);
        Resource file = storageService.loadAsResource(filename);
//        extensionService.incrementDownloadsCount(extensionDetailsView);
        extensionService.incrementDownloadsCount(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("add/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/extensions/all";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
