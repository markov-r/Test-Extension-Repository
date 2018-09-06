package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.exceptions.StorageFileNotFoundException;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private StorageService storageService;


//    @GetMapping("/")
//    public String getHomePage(Model model){
//        model.addAttribute("view","/extensions/extensions-table");
//        return "base-layout";
//    }

    // WO
    @GetMapping("/")
    public String getAllExtensionPage(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAll();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("public/new")
    public String getNewestExtensionsPage(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllSortedByDate();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("public/featured")
    public String getFeaturedExtensions(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllFeatured();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("public/popular")
    public String getPopularExtensions(Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllSortedByPopularity();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

    // get Extension details page WO
    @GetMapping("public/{id}")
    public String getExtensionDetailsPage(Model model, @PathVariable Long id) {
        ExtensionDto extensionDto = this.extensionService.getByIdToDetailsPage(id);
        model.addAttribute("extension", extensionDto);
        model.addAttribute("view", "/extensions/details");
        return "base-layout";
    }

    @GetMapping("public/download/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {

        String filename = this.extensionService.findFilename(id);
        Resource file = storageService.loadAsResource(filename);
        extensionService.incrementDownloadsCount(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/unauthorized")
//    public String getNoAccessPage(Model model) {
//        model.addAttribute("view", "no-access");
//        return "base-layout";
//    }
}