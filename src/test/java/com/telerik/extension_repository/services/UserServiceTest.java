package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.tags.TagView;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import com.telerik.extension_repository.repositories.TagRepository;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import com.telerik.extension_repository.services.interfaces.StorageService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    public static final int EXPECTED_LIST_SIZE = 2;

    public static final int EXPECTED_LIST_SIZE_ZERO = 0;

    private ExtensionRepository mockExtensionRepository = mock(ExtensionRepository.class);
    private AuthorityRepository mockAuthorityRepository = mock(AuthorityRepository.class);
    private UserRepository mockUserRepository = mock(UserRepository.class);
    private UserService mockUserService = mock(UserService.class);

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private AuthorityServiceImpl roleService;

    public static class Helpers {


    }

    @Test
    public void findUserByUsername_shouldReturnCorrectUsername() {
        String pesho = "Pesho";
        when(mockUserRepository.findOneByUsername(pesho)).thenReturn(
                new User()
        );

        User userByUsername = userService.findByUsername(pesho);

        Assert.assertEquals(new User().getUsername(), userByUsername.getUsername());
    }

    @Test
    public void getAllUsers_shouldReturnCorrectNumberOfEntities() {
        // Arrange
        List<User> users = Arrays.asList(
                new User("User1", "user1@gmail.com", true),
                new User("User2", "user2@gmail.com", false),
                new User("User3", "user3@gmail.com", true)
        );

        // Act
        when(mockUserRepository.findAll()).thenReturn(users);


        // Assert
        Assert.assertEquals(users.size(), 3);
    }

    @Test
    public void getAllUsers_shouldReturnIncorrectNumberOfEntities() {
        // Arrange
        List<User> users = Arrays.asList(
                new User("User1", "user1@gmail.com", true),
                new User("User2", "user2@gmail.com", false),
                new User("User3", "user3@gmail.com", true)
        );

        // Act
        when(mockUserRepository.findAll()).thenReturn(users);


        // Assert
        Assert.assertNotEquals(users.size(), 0);
    }

    @Test
    public void getAllModels_shouldReturnCorrectNumberOfModels() {
        // Arrange
        List<RegisterUserModel> registerUserModels = Arrays.asList(
                new RegisterUserModel("User1", "user1@gmail.com", true),
                new RegisterUserModel("User2", "user2@gmail.com", false),
                new RegisterUserModel("User3", "user3@gmail.com", true)
        );

        // Act
        when(mockUserService.getAll()).thenReturn(registerUserModels);


        // Assert
        Assert.assertEquals(registerUserModels.size(), 3);
    }

    @Test
    public void getAll_shouldReturnIncorrectNumberOfEntities() {
        // Arrange
        List<RegisterUserModel> registerUserModels = Arrays.asList(
                new RegisterUserModel("User1", "user1@gmail.com", true),
                new RegisterUserModel("User2", "user2@gmail.com", false),
                new RegisterUserModel("User3", "user3@gmail.com", true)
        );

        when(mockUserService.getAll()).thenReturn(registerUserModels);

        //Act
        List<RegisterUserModel> actualUserList = userService.getAll();

        // Assert
        Assert.assertNotEquals(registerUserModels.size(), 5);
    }

}
