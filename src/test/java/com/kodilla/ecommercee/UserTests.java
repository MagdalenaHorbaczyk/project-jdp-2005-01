package com.kodilla.ecommercee;

import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // given
        User user = new User();
        user.setUsername("adam");
        user.setUserKey("a13tcssdf");

        // when
        userRepository.save(user);
        // then
        System.out.println(user.getUserId());
        Assert.assertNotNull(user.getUserId());
    }
}
