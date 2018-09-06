//package com.telerik.extension_repository.services;
//
//import com.telerik.extension_repository.services.interfaces.FileStorageService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Service
//public class FileStorageServiceImpl implements FileStorageService {
//
//    Logger log = LoggerFactory.getLogger(this.getClass().getName());
//    private final Path rootLocation = Paths.get("upload-dir");
//
//    //yes
//    @Override
//    public void init() {
//        try {
//            Files.createDirectory(rootLocation);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize storage!");
//        }
//    }
//
//    //yes
//    @Override
//    public void store(MultipartFile file) {
//        try {
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
//        } catch (Exception e) {
//            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
//        }
//    }
//
//    @Override
//    public Resource loadFile(String filename) {
//        try {
//            Path file = rootLocation.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if(resource.exists() || resource.isReadable()) {
//                return resource;
//            }else{
//                throw new RuntimeException("FAIL!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error! -> message = " + e.getMessage());
//        }
//    }
//
//    @Override
//    public Resource loadAsResource(String filename) {
//        try {
//            Path file = rootLocation.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read file: " + filename);
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Could not read file: " + filename, e);
//        }
//    }
//
//    @Override
//    public void delete(String filename) {
//        Path file = rootLocation.resolve(filename);
//
//        try {
//            FileSystemUtils.deleteRecursively(file);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
//}