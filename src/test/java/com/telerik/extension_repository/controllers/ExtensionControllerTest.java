package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExtensionControllerTest {
    @MockBean
    ExtensionService extensionService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void homePageShouldRedirectToLogin() throws Exception {
        List<Extension> extensions = Arrays.asList(
                new Extension(),
                new Extension()
        );
//        when(extensionService.getAllExt())
//                .thenReturn(extensions);
        ResultActions expect = mockMvc.perform(
                get("")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
