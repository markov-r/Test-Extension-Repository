package com.telerik.extension_repository.services;


//import com.telerik.extension_repository.controllers.DownloadController;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.entities.Tag;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.viewModels.extensions.*;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExtensionServiceImpl implements ExtensionService {

    private final ExtensionRepository extensionRepository;
    private final ModelMapper modelMapper;
    private final GithubApiService githubApiService;
    private UserRepository userRepository;
    private TagRepository tagRepository;
    private FileSystemStorageService fileStorageService;

    @Autowired
    public ExtensionServiceImpl(@Lazy ExtensionRepository extensionRepository, @Lazy
            ModelMapper modelMapper, @Lazy
                                        GithubApiService githubApiService, @Lazy
                                        UserRepository userRepository, @Lazy TagRepository tagRepository,
                                @Lazy FileSystemStorageService fileStorageService) {
        this.extensionRepository = extensionRepository;
        this.modelMapper = modelMapper;
        this.githubApiService = githubApiService;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<ExtensionDto> getAllPending() {
        List<Extension> extensions = this.extensionRepository.findAllByStatus(Status.PENDING);
        List<ExtensionDto> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto extensionModelView = this.modelMapper.map(extension, ExtensionDto.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }

    @Override
    public List<ExtensionDto> getAllApproved() {
        List<Extension> extensions = this.extensionRepository.findAllByStatus(Status.APPROVED);
        List<ExtensionDto> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto extensionModelView = this.modelMapper.map(extension, ExtensionDto.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }

    @Override
    public List<ExtensionDto> getAllFeatured() {

       return this.extensionRepository.findAllFeatured()
                .stream()
                .map(e -> this.modelMapper.map(e, ExtensionDto.class))
                .limit(8)
                .collect(Collectors.toList());
       }

    @Override
    public List<ExtensionDto> getAllSortedByDate() {
        List<Extension> extensions = this.extensionRepository.getAllSortedByDate();
        List<ExtensionDto> modelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto model = this.modelMapper.map(extension, ExtensionDto.class);
            modelViews.add(model);
        }
        return modelViews;
    }


    @Override
    public List<ExtensionDto> getAll() {
        List<Extension> extensions = this.extensionRepository.findAll();
        List<ExtensionDto> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto extensionModelView = this.modelMapper.map(extension, ExtensionDto.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }

    @Override
    public List<ExtensionDto> getAllExt() {
        List<Extension> extensions = this.extensionRepository.findAll();
        List<ExtensionDto> extensionDtos = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto model = this.modelMapper.map(extension, ExtensionDto.class);
            extensionDtos.add(model);
        }
        return extensionDtos;
    }

    @Override
    public ExtensionDto getByIdToDetailsPage(Long id) {
        Extension extension = this.extensionRepository.getOne(id);
        ModelMapper modelMapper = new ModelMapper();
        ExtensionDto extensionModel = null;
        if (extension != null) {
            extensionModel = modelMapper.map(extension, ExtensionDto.class);
        }
        return extensionModel;
    }

    @Override
    public List<ExtensionDto> getAllByName(String name) {
        List<Extension> extensions = new ArrayList<>();
        if (name != null) {
            extensions = this.extensionRepository.getAllByNameOrderByNameAsc(name);
        } else {
            extensions = this.extensionRepository.findAll();
        }

        List<ExtensionDto> extensionModelViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Extension extension : extensions) {
            ExtensionDto extensionModelView = modelMapper.map(extension, ExtensionDto.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }

    @Override
    public List<ExtensionDto> getAllJsons() {
        List<Extension> extensions = this.extensionRepository.findAll();
        List<ExtensionDto> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto extensionModelView = this.modelMapper.map(extension, ExtensionDto.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }


    @Override
    public List<Extension> getAllExtensions() {
        return this.extensionRepository.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void persist(ExtensionDto addExtensionModel) {
        ModelMapper modelMapper = new ModelMapper();
        Extension extension = modelMapper.map(addExtensionModel, Extension.class);
        //Extension extension = new Extension();
        extension.setStatus(Status.PENDING);
        extension.setName(addExtensionModel.getName());
        extension.setDescription(addExtensionModel.getDescription());
        extension.setSource_repository_link(addExtensionModel.getSource_repository_link());

        GitHubData gitHubData = this.getGitHubData(extension);
        extension.setGitHubData(gitHubData);
        User userEntity = this.getCurrentUser();
        extension.setOwner(userEntity);
        HashSet<Tag> tags = getTags(addExtensionModel);
        extension.setTags(tags);
//         //MvcUriComponentsBuilder prepares the URL based on the method which is going to actually serve the file for download
//        String downloadLink =  MvcUriComponentsBuilder.fromMethodName(ExtensionController.class,
//                "serveFile", addExtensionModel.getMultipartFile().getOriginalFilename()).build().toString();
//        extension.setDownloadLink(downloadLink);
        //System.out.println(addExtensionModel.getFile().getName());
        System.out.print(addExtensionModel.getFile().getOriginalFilename());
        extension.setFileName(addExtensionModel.getFile().getOriginalFilename());
        this.extensionRepository.saveAndFlush(extension);
    }

    private HashSet<Tag> getTags(ExtensionDto addExtensionModel) {
        return this.findTagsFromString(addExtensionModel.getTagString());
    }

    private User getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return this.userRepository.findOneByUsername(user.getUsername());
    }

    public String findFilename(Long id) {
        return this.extensionRepository.findByFilename(id);
    }

    @Override
    public void incrementDownloadsCount(Long id) {
//    public void incrementDownloadsCount(ExtensionDto extensionDto) {
        ExtensionDto extensionDto = this.getById(id);
        this.extensionRepository.incrementDownloadsCount(extensionDto.getNumberOfDownloads() + 1, extensionDto.getId());
    }

    @Override
    public List<ExtensionDto> getAllSortedByPopularity() {
        List<Extension> extensions = this.extensionRepository.getAllSortedByPopularity();
        List<ExtensionDto> extensionDtoList = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDto extensionDto = this.modelMapper.map(extension, ExtensionDto.class);
            extensionDtoList.add(extensionDto);
        }
        return extensionDtoList;
    }

    private GitHubData getGitHubData(Extension extension) {
        String fullUrl = extension.getSource_repository_link();

        GitHubData gitHubData = null;
        try {
            gitHubData = githubApiService.updateGithubData(fullUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return gitHubData;
    }

    @Override
    public void approve(Long id) {
//        addExtensionModel.setStatus(Status.APPROVED);
//        ModelMapper modelMapper = new ModelMapper();
//        Extension extension = modelMapper.map(addExtensionModel, Extension.class);
//        this.extensionRepository.saveAndFlush(extension);
        this.extensionRepository.approveExtension(id);
    }

//    @Override
//    public ExtensionDto getByIdToEdit(Long id) {
//        Extension extension = this.extensionRepository.getOne(id);
//        ModelMapper modelMapper = new ModelMapper();
//        ExtensionDto extensionModel = null;
//        if (extension != null) {
//            extensionModel = modelMapper.map(extension, ExtensionDto.class);
//
//        }
//        return extensionModel;
//
//
//    }

    private boolean isUserAuthorOrAdmin(Extension article) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findOneByUsername(user.getUsername());

        return userEntity.isAdmin() || userEntity.isAuthor(article);
    }

    @Override
    public ExtensionDto getById(Long id) {
        Optional<Extension> extension = this.extensionRepository.findById(id);
        ExtensionDto extensionStatusView = modelMapper.map(extension, ExtensionDto.class);
        return extensionStatusView;
    }

    @Override
    public void update(ExtensionDto extensionModel) {
        this.extensionRepository.update(extensionModel.getName(), extensionModel.getDescription(), extensionModel.getStatus(), extensionModel.getId());
    }

    @Override
    public void setFeatured(Long id) {
        extensionRepository.setFeatured(id);
    }

    @Override
    public void removeFeatured(Long id) {
        extensionRepository.removeFeatured(id);
    }

    @Override
    public void delete(Long id) {
        this.extensionRepository.deleteById(id);
    }


    private HashSet<Tag> findTagsFromString(String tagString) {
        HashSet<Tag> tags = new HashSet<>();
        String[] tagNames = tagString.split(",\\s*");

        for (String tagName : tagNames) {
            Tag currentTag = this.tagRepository.findByName(tagName);

            if (currentTag == null) {
                currentTag = new Tag(tagName);
                this.tagRepository.saveAndFlush(currentTag);
            }

            tags.add(currentTag);
        }

        return tags;
    }


}
