package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.entities.Tag;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ExtensionService extensionService;

    @GetMapping("/public/searchByName")
    public String extensionsWithName(Model model, @RequestParam(value="name", required = false) String name){
        List<ExtensionDto> extensions = this.extensionService.getAllMatchingKeywordOrderByName(name);

        if(extensions == null){
            return "redirect:/extensions/all";
        }

        model.addAttribute("view", "/extensions/extensions-table");
        model.addAttribute("extensions", extensions);

        return "base-layout";
    }

    @GetMapping("/public/search")
    public String browseExtensions(@RequestParam(value="name", required = false) String name,
                                   @RequestParam(value="sortBy", required = false) String sortBy,
                                   Model model) {
        List<ExtensionDto> extensions = extensionService.filter(name, sortBy);

        model.addAttribute("extensions", extensions);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }
}
