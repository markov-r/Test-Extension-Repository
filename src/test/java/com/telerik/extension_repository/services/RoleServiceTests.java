package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.models.bindingModels.user.AuthorityModel;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTests {
    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthorityServiceImpl authorityService;

    @Test
    public void findByName_shouldReturnRole_whenExistingNameGiven() {
        // Arrange
        Authority role = new Authority();
        role.setAuthority("MOCK_ROLE");

        Mockito.when(authorityRepository.getByAuthority("MOCK_ROLE")).thenReturn(role);

        // Act
        AuthorityModel actualRole = authorityService.findByName("MOCK_ROLE");

        // Assert
        Assert.assertEquals(role.getAuthority(), actualRole.getAuthority());
    }
}
