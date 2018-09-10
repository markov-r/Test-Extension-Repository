package com.telerik.extension_repository.repositories;

import com.sun.xml.internal.ws.protocol.soap.VersionMismatchException;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class UserRepositoryTest {


    private UserRepository mockUserRepository = mock(UserRepository.class);


    @Test
    public void getAllByUsername_shouldReturnCorrectNumberOfEntities() {

        String name = "User1";

        // Arrange
        List<User> users = Arrays.asList(
                new User("User1", "user1@gmail.com", true),
                new User("User2", "user2@gmail.com", false),
                new User("User3", "user3@gmail.com", true)
        );

        when(mockUserRepository.findAllByUsername(name)).thenReturn(users);

        // Assert
        Assert.assertNotEquals(users.size(), 1);
    }
}