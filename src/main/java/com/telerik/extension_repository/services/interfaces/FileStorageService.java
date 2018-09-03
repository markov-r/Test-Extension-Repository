package com.telerik.extension_repository.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public void init();
    public void store(MultipartFile file);
    public Resource loadFile(String filename);
    Resource loadAsResource(String filename);
    void delete(String filename);
}
