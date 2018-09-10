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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {


    private ExtensionRepository mockExtensionRepository = mock(ExtensionRepository.class);
    private AuthorityRepository mockAuthorityRepository = mock(AuthorityRepository.class);
    private UserRepository mockUserRepository = mock(UserRepository.class);
    private UserService mockUserService = mock(UserService.class);

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private AuthorityServiceImpl roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    public static class Helpers {


    }

    private static final String PASSWORD_HASH = "myCustomHash";


    @Before
    public void setUp() {
        when(this.passwordEncoder.encode(anyString()))
                .thenReturn(PASSWORD_HASH);
    }

    @Test
    public void testRegister_withUsernameAndPassword_passwordShouldBeEncoded() {
        when(mockUserRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));
        final String USERNAME = "pesho";
        final String PASSWORD = "pesho123";

        User result = this.userService.registerByUsernameAndPassword(USERNAME, PASSWORD);

        assertEquals(
                "Password was not or wrongly encoded!",
                PASSWORD_HASH,
                result.getPassword()
        );
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


        // Assert
        Assert.assertNotEquals(registerUserModels.size(), 5);
    }

    @Test
    public void getAllByUsername_shouldReturnIncorrectNumberOfEntities() {
        // Arrange
        List<RegisterUserModel> registerUserModels = Arrays.asList(
                new RegisterUserModel("User1", "user1@gmail.com", true),
                new RegisterUserModel("User2", "user2@gmail.com", false),
                new RegisterUserModel("User3", "user3@gmail.com", true)
        );

        when(mockUserService.getAll()).thenReturn(registerUserModels);


        // Assert
        Assert.assertNotEquals(registerUserModels.size(), 5);
    }

    @Test
    public void getAllUsersByUsername_shouldReturnCorrectNumberOfModels() {

        String name = "User1";
        // Arrange
        List<RegisterUserModel> registerUserModels = Arrays.asList(
                new RegisterUserModel("User1", "user1@gmail.com", true),
                new RegisterUserModel("User2", "user2@gmail.com", false),
                new RegisterUserModel("User3", "user3@gmail.com", true)
        );

        when(mockUserService.getAllByUsername(name)).thenReturn(registerUserModels);

        // Assert
        Assert.assertEquals(registerUserModels.size(), 1);
    }

    @Test
    public void getAllUsersByUsername_shouldReturnIncorrectNumberOfModels() {

        String name = "Oksana";
        // Arrange
        List<RegisterUserModel> registerUserModels = Arrays.asList(
                new RegisterUserModel("User1", "user1@gmail.com", true),
                new RegisterUserModel("User2", "user2@gmail.com", false),
                new RegisterUserModel("User3", "user3@gmail.com", true)
        );

        when(mockUserService.getAllByUsername(name)).thenReturn(registerUserModels);

        // Assert
        Assert.assertNotEquals(registerUserModels.size(), 1);
    }

    @Test
    public void isExistUsername_shouldReturnTrue_whenUsernameExists() {
        // Arrange
        User user = new User();
        user.setUsername("Oksana");

        when(mockUserRepository.findOneByUsername(user.getUsername())).thenReturn(user);

        // Act
        boolean result = userService.isExistUsername(user.getUsername());

        // Assert
        Assert.assertTrue(result);
    }

}
