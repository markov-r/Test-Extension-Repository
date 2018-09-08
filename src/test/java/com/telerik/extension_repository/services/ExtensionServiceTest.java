package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.viewModels.tags.TagView;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import com.telerik.extension_repository.services.interfaces.StorageService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExtensionServiceTest {

    public static final int EXPECTED_LIST_SIZE = 2;

    public static final int EXPECTED_LIST_SIZE_ZERO = 0;

    private GithubApiService mockGitHubService = mock(GithubApiService.class);
    private ExtensionRepository mockExtensionRepository = mock(ExtensionRepository.class);
    private StorageService mockFileService = mock(StorageService.class);
    private TagRepository mockTagRepository = mock(TagRepository.class);
    private UserService mockUserService = mock(UserService.class);

    @InjectMocks
    private ExtensionServiceImpl extensionService;

    public static class Helpers {
        public static ExtensionDto createFakeExtensionDto(){
            ExtensionDto dto = new ExtensionDto();
            dto.setName("name");
            dto.setDescription("description");
            dto.setSource_repository_link("github");
            dto.setTags(new HashSet<TagView>(
                    Arrays.asList(
                            new TagView("Java"),
                            new TagView("Demo"),
                            new TagView("Extension"))));
            return dto;
        }

        public static List<ExtensionDto> createFakeExtensionList(){
            List<ExtensionDto> extensions = Arrays.asList(
                    new ExtensionDto("First Extension1", "First Extension1 description", "1.009.1", 45, "1.1/link", "1.1.zip", true, Status.PENDING,  new LoggedUser()),
                    new ExtensionDto("First Extension2", "First Extension2 description", "1.05.1", 5, "1.2/link", "1.2.zip", true, Status.APPROVED,  new LoggedUser()),
                    new ExtensionDto("Second Extension1", "Second Extension1 description", "8.009.1", 459, "2.1/link", "2.1.zip", false, Status.PENDING,  new LoggedUser()),
                    new ExtensionDto("Second Extension2", "Second Extension2 description", "9.1", 87, "2.2/link", "2.2.zip", true, Status.PENDING,  new LoggedUser()),
                    new ExtensionDto("Third Extension1", "Third Extension1 description", "67.1.09", 7776445, "3.1/link", "3.1.zip", false, Status.APPROVED,  new LoggedUser()),
                    new ExtensionDto("Third Extension2", "Third Extension2 description", "19.89.1", 48888888, "3.2/link", "3.1.zip", false, Status.APPROVED,  new LoggedUser()),
                    new ExtensionDto("Fourth Extension1", "Fourth Extension1 description", "21.009.1", 641545, "4.1/link", "4.1.zip", true, Status.PENDING,  new LoggedUser())
            );
            return extensions;
        }
    }

    @Test
    public void listAll_shouldReturnAListOfExtensions() {
        when(mockExtensionRepository.findAll()).thenReturn(
                new ArrayList<>()
        );
        List<ExtensionDto> result = extensionService.getAllExt();
        Assert.assertEquals(new ArrayList<Extension>(), result);
    }


    @Test
    public void findAllExtensionsShouldPassed(){



    }

//    @Test
//    public void delete_shouldReturnExtension_ifUpdateSuccessful() {
//        // Arrange
//        Extension extension = new Extension();
//        Extension extension1 = new Extension();
//
//
//        when(extensionRepository.delete(extension)).thenReturn(extension);
//        when(extensionRepository.delete(extension1)).thenReturn(null);
//
//        // Act
//        boolean isDeleteSuccessful = extensionService.delete(extension);
//        boolean failedDelete = extensionService.delete(extension1);
//
//        // Assert
//        Assert.assertTrue(isDeleteSuccessful);
//        Assert.assertFalse(failedDelete);
//    }

}
