package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.repositories.UserRepository;
import com.telerik.extension_repository.services.interfaces.AuthorityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityService authorityService;

    @Mock
    private ModelMapper mapper;

    @Mock
    private BCryptPasswordEncoder encoder;

    private final String EMAIL_EXISTS = "oxana@gmail.com";
    private final String EMAIL_AVAILABLE = "new-user@mail.ru";
    private final String USERNAME_EXISTS = "exists";
    private final String USERNAME_AVAILABLE = "available";
    private final String PASS_OLD = "123456";
    private final String PASS_NEW = "234567";


}