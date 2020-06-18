package com.kodilla.ecommercee.entity;

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
public class OrderTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveOrder() {
        //Given
        Order order = new Order();
        order.setStatus("new");
        order.setPaymentMethod("credit card");
        order.setCreationDate(LocalDate.of(2020, 06, 8));
        order.setDeliveryMethod("inPost");
        // When
        orderRepository.save(order);
        Long orderId = order.getOrderId();
        // Then
        Assert.assertNotNull(orderId);
        //Clean-up
        orderRepository.deleteById(orderId);
    }

    @Test
    public void testReadOrder() {
        //Given
        Order order = new Order();
        order.setStatus("new");
        order.setPaymentMethod("credit card");
        order.setCreationDate(LocalDate.of(2020, 06, 8));
        order.setDeliveryMethod("inPost");
        // When
        orderRepository.save(order);
        Long orderId = order.getOrderId();
        //Then
        Assert.assertNotNull(orderRepository.findById(orderId));
        //Clean-up
        orderRepository.deleteById(orderId);
    }

    @Test
    public void testUpdateOrder() {
        //Given
        Order order = new Order();
        order.setStatus("new");
        order.setPaymentMethod("credit card");
        order.setCreationDate(LocalDate.of(2020, 06, 8));
        order.setDeliveryMethod("inPost");
        orderRepository.save(order);
        Long orderId = order.getOrderId();
        Order fetchedOrder = orderRepository.findById(orderId).orElse(null);
        assert fetchedOrder != null;
        String status = fetchedOrder.getStatus();
        //When
        fetchedOrder.setStatus("updated status");
        orderRepository.save(fetchedOrder);
        String updatedStatus = fetchedOrder.getStatus();
        //Then
        Assert.assertNotEquals(status, updatedStatus);
        //Clean-up
        orderRepository.deleteById(orderId);
    }

    @Test
    public void testDeleteOrder() {
        //Given
        Order order = new Order();
        order.setStatus("new");
        order.setPaymentMethod("credit card");
        order.setCreationDate(LocalDate.of(2020, 06, 8));
        order.setDeliveryMethod("inPost");
        orderRepository.save(order);
        Long orderId = order.getOrderId();
        //When
        orderRepository.deleteById(orderId);
        //Then
        Assert.assertFalse(orderRepository.existsById(orderId));
    }

    @Test
    public void testDeleteOrderUser() {
        //Given
        Order order = new Order();
        order.setStatus("new");
        order.setPaymentMethod("credit card");
        order.setCreationDate(LocalDate.of(2020, 06, 8));
        order.setDeliveryMethod("inPost");
        User user = new User();
        user.setUserKey("abc");
        user.setUsername("John");
        orderRepository.save(order);
        userRepository.save(user);
        user.getOrders().add(order);
        order.setUser(user);
        orderRepository.save(order);
        userRepository.save(user);
        Long orderId = order.getOrderId();
        Long userId = user.getUserId();
        //When
        orderRepository.deleteById(orderId);
        //Then
        Assert.assertFalse(orderRepository.existsById(orderId));
        Assert.assertTrue(userRepository.existsById(userId));
        //Clean-up
        userRepository.deleteById(userId);
    }

    @Test
    public void testDeleteUserOrder() {
        //Given
        Order order = new Order();
        order.setStatus("new");
        order.setPaymentMethod("credit card");
        order.setCreationDate(LocalDate.of(2020, 06, 8));
        order.setDeliveryMethod("inPost");
        User user = new User();
        user.setUserKey("abc");
        user.setUsername("John");
        orderRepository.save(order);
        userRepository.save(user);
        user.getOrders().add(order);
        order.setUser(user);
        orderRepository.save(order);
        userRepository.save(user);
        Long orderId = order.getOrderId();
        Long userId = user.getUserId();
        //When
        userRepository.deleteById(userId);
        //Then
        Assert.assertFalse(userRepository.existsById(userId));
        Assert.assertFalse(orderRepository.existsById(orderId));
    }
}