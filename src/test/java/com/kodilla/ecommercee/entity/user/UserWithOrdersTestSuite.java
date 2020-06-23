package com.kodilla.ecommercee.entity.user;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.repository.OrderRepository;
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

    @Autowired
    OrderRepository orderRepository;

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
        long orderId = order1.getOrderId();

        //Then
        Assert.assertNotEquals(0, idUserAnna);
        Assert.assertTrue(userRepository.existsById(idUserAnna));
        Assert.assertTrue(orderRepository.existsById(orderId));

        //CleanUp
        userRepository.deleteById(idUserAnna);
    }

    @Test
    public void testUserWithOrdersDelete() {
        User userAnna = new User();
        userAnna.setUsername("Anna");
        userAnna.setUserKey("01");

        Order order1 = new Order();
        order1.setCreationDate(LocalDate.now());
        order1.setStatus("In progress");
        order1.setDeliveryMethod("GLS");
        order1.setPaymentMethod("cash");

        userAnna.getOrders().add(order1);
        order1.setUser(userAnna);

        userRepository.save(userAnna);
        long idUserAnna = userAnna.getUserId();
        long orderId = order1.getOrderId();

        //When
        userRepository.delete(userAnna);

        //Then
        Assert.assertFalse(userRepository.existsById(idUserAnna));
        Assert.assertFalse(orderRepository.existsById(orderId));

    }

    @Test
    public void testUserWithOrdersUpdate() {
        User user = new User();
        user.setUsername("Anna");
        user.setUserKey("01");

        Order order1 = new Order();
        order1.setCreationDate(LocalDate.now());
        order1.setStatus("In progress");
        order1.setDeliveryMethod("GLS");
        order1.setPaymentMethod("cash");

        user.getOrders().add(order1);
        order1.setUser(user);

        userRepository.save(user);
        long idUserAnna = user.getUserId();
        long orderId = order1.getOrderId();
        String userKey1 = user.getUserKey();
        String status1 = order1.getStatus();

        //When
        User modifiedUser = userRepository.findById(idUserAnna).orElse(null);
        modifiedUser.setUserKey("02");

        Order modifiedOrder = orderRepository.findById(orderId).orElse(null);
        modifiedOrder.setStatus("Done");

        userRepository.save(modifiedUser);
        String userKey2 = modifiedUser.getUserKey();
        String status2 = modifiedOrder.getStatus();

        //Then
        Assert.assertEquals(1, userRepository.count());
        Assert.assertNotEquals(userKey1, userKey2);
        Assert.assertNotEquals(status1, status2);

        //CleanUp
        userRepository.deleteById(idUserAnna);
    }
}













