package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest_api/extensions")
public class RestHomeController {

    private ExtensionService extensionService;

    @Autowired
    public RestHomeController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @GetMapping("/")
    public List<ExtensionDto> getAllExtensions(){
        return this.extensionService.getAllJsons();
    }

    @GetMapping("/pending/")
    List<ExtensionDto> findByName() {
        return extensionService.getAllPending();
    }

}