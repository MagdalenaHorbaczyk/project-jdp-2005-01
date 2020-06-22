package com.kodilla.ecommercee.entity.user;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserWithCartTestSuite {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @Test
    public void testUserWithCartSave() {
        //Given
        User userAnna = new User();
        userAnna.setUsername("Anna");
        userAnna.setUserKey("01");

        Cart cartUserAnna = new Cart();

        userAnna.setCart(cartUserAnna);

        //When
        userRepository.save(userAnna);

        long idUserAnna = userAnna.getUserId();
        long idCartAnna = userAnna.getCart().getCartId();

        List<Cart> carts = cartRepository.findAll();


        //Then
        Assert.assertNotEquals(0, idUserAnna);
        Assert.assertNotEquals(0, idCartAnna);
        Assert.assertEquals(1, carts.size());

        //CleanUp
        userRepository.deleteById(idUserAnna);
    }

    @Test
    public void testUserWithCartDelete() {
        //Given
        User userAnna = new User();
        userAnna.setUsername("Anna");
        userAnna.setUserKey("01");

        Cart cartUserAnna = new Cart();
        userAnna.setCart(cartUserAnna);

        userRepository.save(userAnna);
        long idUserAnna = userAnna.getUserId();
        long idCartAnna = userAnna.getCart().getCartId();

        //When
        userRepository.deleteById(idUserAnna);

        List<Cart> carts = cartRepository.findAll();

        //Then
        Assert.assertFalse(userRepository.existsById(idUserAnna));
        Assert.assertFalse(cartRepository.existsById(idCartAnna));
        Assert.assertEquals(0, carts.size());

        //CleanUp
        //
    }

    @Test
    public void testUserWithCartUpdate() {//teraz to
    }
}












