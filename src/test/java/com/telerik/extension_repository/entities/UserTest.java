package com.telerik.extension_repository.entities;

import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserTest {

    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    private static final String EXPECTED_ROLE_NAME = "ROLE_USER";

    public static final int EXPECTED_LIST_SIZE = 1;

    private User user;

    @Mock
    private Authority authority;

    @Before
    public void setUp() {
        //Arrange
        this.user = new User();
        when(this.authority.getAuthority()).thenReturn(EXPECTED_ROLE_NAME);
    }

    @Test
    public void addRoleWhenUserRoleGivenShouldReturnCorrectRoleName() {
        //Act
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        this.user.setAuthorities(authorities);
        System.out.println(authorities);

        //Assert
        String actualRoleName = this.user
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        assertEquals(EXPECTED_ROLE_NAME, actualRoleName);
    }

//    @Test
//    public void addToLikedTestShouldReturnCorrectListSize() throws Exception {
//        //Act
//        this.user.addToLiked(new Meme());
//        //Assert
//        int likedMemesSize = this.user.getLikedMemes().size();
//        assertEquals(EXPECTED_LIST_SIZE, likedMemesSize);
//    }

//    @Test
//    public void addToDislikedTestShouldReturnCorrectListSize() throws Exception {
//        //Act
//        this.user.addToDisliked(new Meme());
//        //Assert
//        int dislikedMemesSize = this.user.getDislikedMemes().size();
//        assertEquals(EXPECTED_LIST_SIZE, dislikedMemesSize);
//    }
}
