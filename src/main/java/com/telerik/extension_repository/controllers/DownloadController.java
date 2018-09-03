//package com.telerik.extension_repository.controllers;
//
//import com.telerik.extension_repository.services.interfaces.ExtensionService;
//import com.telerik.extension_repository.services.interfaces.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class DownloadController {
//
//    private FileStorageService fileStorageService;
//
//    @Autowired
//    private ExtensionService extensionService;
//
//    @Autowired
//    public DownloadController(FileStorageService fileStorageService) {
//        this.fileStorageService = fileStorageService;
//    }
//
//    @GetMapping("/extensions/{id}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
//        String filename = this.extensionService.findFilename(id);
//        Resource file = fileStorageService.loadFile(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }
//}
