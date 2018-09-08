package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExtensionServiceTest {

    public static final int EXPECTED_LIST_SIZE = 2;

    public static final int EXPECTED_LIST_SIZE_ZERO = 0;

    @Mock
    private ExtensionRepository extensionRepository;

    @InjectMocks
    private ExtensionServiceImpl extensionService;

    @Autowired
    private UserService userService;


    @Test
    public void findAllExtensionsShouldPassed(){

        List<Extension> extensions = Arrays.asList(
                new Extension("First Extension1", "First Extension1 description", "1.009.1", 45, "1.1/link", "1.1.zip", true, Status.PENDING,  new User()),
                new Extension("First Extension2", "First Extension2 description", "1.05.1", 5, "1.2/link", "1.2.zip", true, Status.APPROVED,  new User()),
                new Extension("Second Extension1", "Second Extension1 description", "8.009.1", 459, "2.1/link", "2.1.zip", false, Status.PENDING,  new User()),
                new Extension("Second Extension2", "Second Extension2 description", "9.1", 87, "2.2/link", "2.2.zip", true, Status.PENDING,  new User()),
                new Extension("Third Extension1", "Third Extension1 description", "67.1.09", 7776445, "3.1/link", "3.1.zip", false, Status.APPROVED,  new User()),
                new Extension("Third Extension2", "Third Extension2 description", "19.89.1", 48888888, "3.2/link", "3.1.zip", false, Status.APPROVED,  new User()),
                new Extension("Fourth Extension1", "Fourth Extension1 description", "21.009.1", 641545, "4.1/link", "4.1.zip", true, Status.PENDING,  new User())
        );

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
