//package com.telerik.extension_repository.controllers;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import com.telerik.extension_repository.services.interfaces.UserService;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//@ActiveProfiles("test")
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void registerUserShouldPass() throws Exception {
//
//        mockMvc
//                .perform(post("localhost:8080/users/register")
////                        .header("host", "localhost:8080/users")
//                        .param("username", "adobrev")
//                        .param("email", "angeldobrev@abv.bg")
//                        .param("password", "asd123456")
//                        .param("confirmPassword", "asd123456"))
//
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/login"))
//                .andExpect(redirectedUrl("/login"))
//                .andExpect(model().hasNoErrors());
//
//        ArgumentCaptor<RegisterUserModel> captor = ArgumentCaptor.forClass(RegisterUserModel.class);
//        verify(userService).register(captor.capture());
//        RegisterUserModel registerUserModel = captor.getValue();
//        assertEquals("angeldobrev@abv.bg", registerUserModel.getEmail());
//        assertEquals("adobrev", registerUserModel.getUsername());
//    }
//
//    @Test(expected = Exception.class)
//    public void registerUserWithDifferentPasswordsShouldThrow() throws Exception {
//        mockMvc
//                .perform(post("localhost:8080/users/register")
//                        .param("email", "purvan@abv.bg")
//                        .param("password", "123456")
//                        .param("confirmPassword", "qwerty")
//                )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/login"))
//                .andExpect(redirectedUrl("/login"))
//                .andExpect(model().hasNoErrors());
//
//        ArgumentCaptor<RegisterUserModel> captor = ArgumentCaptor.forClass(RegisterUserModel.class);
//        verify(userService).register(captor.capture());
//        RegisterUserModel registerUserModel = captor.getValue();
//        assertEquals("purvan@abv.bg", registerUserModel.getEmail());
//    }
//
//    @Test(expected = Exception.class)
//    public void registerUserWithShortPasswordsShouldThrow() throws Exception {
//        mockMvc
//                .perform(post("localhost:8080/users/register")
//                        .param("email", "ivanoff@abv.bg")
//                        .param("password", "1")
//                        .param("confirmPassword", "1")
//                )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/login"))
//                .andExpect(redirectedUrl("/login"))
//                .andExpect(model().hasNoErrors());
//
//        ArgumentCaptor<RegisterUserModel> captor = ArgumentCaptor.forClass(RegisterUserModel.class);
//        verify(userService).register(captor.capture());
//        RegisterUserModel registerUserModel = captor.getValue();
//        assertEquals("ivanoff@abv.bg", registerUserModel.getEmail());
//    }
//}