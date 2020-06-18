package com.kodilla.ecommercee.entity.user;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserWithOrdersTestSuite {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserWithOrdersSave() {
        //Given
        User userAnna = new User();
        userAnna.setUsername("Anna");
        userAnna.setUserKey("01");

        Order order1 = new Order();
        order1.setCreationDate(LocalDate.now());
        order1.setStatus("In progress");
        order1.setDeliveryMethod("GLS");
        order1.setPaymentMethod("cash");

        Order order2 = new Order();
        order2.setCreationDate(LocalDate.now());
        order2.setStatus("Completed");
        order2.setDeliveryMethod("GLS");
        order2.setPaymentMethod("cash");

        Order order3 = new Order();
        order3.setCreationDate(LocalDate.now());
        order3.setStatus("In progress");
        order3.setDeliveryMethod("InPost");
        order3.setPaymentMethod("Credit cart");

        userAnna.getOrders().add(order1);
        userAnna.getOrders().add(order2);
        userAnna.getOrders().add(order3);

        order1.setUser(userAnna);
        order2.setUser(userAnna);
        order3.setUser(userAnna);

        //When
        userRepository.save(userAnna);

        long idUserAnna = userAnna.getUserId();

        //Then
        Assert.assertNotEquals(0, idUserAnna);

        //CleanUp
        userRepository.deleteById(idUserAnna);
    }
}













