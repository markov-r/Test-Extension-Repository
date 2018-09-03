//package com.telerik.extension_repository.services;
//
//import com.telerik.extension_repository.repositories.ExtensionRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.*;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DataJpaTest
//@ActiveProfiles("test")
//public class ExtensionServiceTest {
//
//    public static final int EXPECTED_LIST_SIZE = 2;
//
//    public static final int EXPECTED_LIST_SIZE_ZERO = 0;
//
//    @Autowired
//    private ExtensionService extensionService;
//
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private ExtensionRepository memeRepository;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @MockBean
//    private MemeService memeServiceMockBean;
//
//    @MockBean
//    private UserService userServiceMockBean;
//
//    private User testUser;
//
//    private User imposterUser;
//
//    @Before
//    public void setUp() throws Exception {
//        testUser = new User();
//        imposterUser = new User();
//
//        Meme firstMeme = new Meme();
//        Meme secondMeme = new Meme();
//
//        List<Meme> testUserMemes = new ArrayList<>();
//        testUserMemes.add(firstMeme);
//        testUserMemes.add(secondMeme);
//
//        testEntityManager.persist(testUser);
//        testEntityManager.persist(imposterUser);
//
//        when(this.memeRepository.findAllByUser(testUser)).thenReturn(testUserMemes);
//    }
//
//    @Test
//    public void findAllMemesByUserShouldPass(){
//
//        List<MemeModel> memes = memeService.getUserMemes(testUser);
//        assertEquals(EXPECTED_LIST_SIZE, memes.size());
//    }
//
//}
