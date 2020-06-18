package com.kodilla.ecommercee.entity.user;

import com.kodilla.ecommercee.entity.Cart;
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
public class UserWithCartTestSuite {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserWithCartSave() {
        //Given
        User userAnna = new User();
        userAnna.setUsername("Anna");
        userAnna.setUserKey("01");
        User userPiotr = new User();
        userPiotr.setUsername("Piotr");
        userPiotr.setUserKey("02");
        User userTom = new User();
        userTom.setUsername("Tomasz");
        userTom.setUserKey("03");

        Cart cartUserAnna = new Cart();

        userAnna.setCart(cartUserAnna);

        //When
        userRepository.save(userAnna);
        userRepository.save(userPiotr);
        userRepository.save(userTom);

        long idUserAnna = userAnna.getUserId();

        //Then
        Assert.assertNotEquals(0, idUserAnna);

        //CleanUp
        userRepository.deleteById(idUserAnna);
    }
}












