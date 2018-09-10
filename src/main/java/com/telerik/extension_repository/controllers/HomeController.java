package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.exceptions.StorageFileNotFoundException;
import com.telerik.extension_repository.models.ExtensionDto;
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
import java.util.stream.Collectors;

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
    public String getCarouselPage(Model model) {
        List<ExtensionDto> featuredExtensions = this.extensionService.getAllFeatured();
        List<ExtensionDto> newestExtensions = this.extensionService.getAllSortedByDate();
        List<ExtensionDto> popularExtensions = this.extensionService.getAllSortedByPopularity();
        model.addAttribute("featured", featuredExtensions);
        model.addAttribute("latest", newestExtensions);
        model.addAttribute("popular", popularExtensions);
        model.addAttribute("view", "/extensions/carousel");
        return "base-layout";
    }

    // get Extension details page WO
    @GetMapping("public/{id}")
    public String getExtensionDetailsPage(Model model, @PathVariable Long id) {
        ExtensionDto extensionDto = this.extensionService.getByIdToDetailsPage(id);
        model.addAttribute("extension", extensionDto);
        model.addAttribute("view", "/extensions/details-site");
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
    public ResponseEntity<?> handleStorageFileNotFound() {
        return ResponseEntity.notFound().build();
    }


    @PostMapping("search/{keyword}")
    public String search(@PathVariable("keyword") String keyword, Model model) {
        List<ExtensionDto> extensionViews = this.extensionService.getAllMatchingNameOrderByName(keyword);
        if(extensionViews == null){
            return "redirect:/extensions/all";
        }
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }



    @GetMapping("/unauthorized")
    public String getNoAccessPage(Model model) {
        model.addAttribute("view", "errors/403");
        return "base-layout";
    }

    @GetMapping("/notFound/404")
    public String getNotFound(Model model) {
        model.addAttribute("view", "errors/404");
        return "base-layout";
    }
}