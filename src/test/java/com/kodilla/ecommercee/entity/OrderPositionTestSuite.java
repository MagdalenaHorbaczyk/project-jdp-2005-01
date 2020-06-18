package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.OrderPositionRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderPositionTestSuite {

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void testSaveOrderPosition() {
        //Given:
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setName("adidas");
        orderPosition.setPrice(new BigDecimal("299"));
        orderPosition.setQuantity(2);

        //When:
        orderPositionRepository.save(orderPosition);
        Long orderPositionId = orderPosition.getOrderPositionId();

        //Then:
        Assert.assertTrue(orderPositionRepository.existsById(orderPositionId));

        //Clean-up:
        orderPositionRepository.deleteById(orderPositionId);
    }

    @Test
    public void testReadOrderPosition() {
        //Given:
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setName("adidas");
        orderPosition.setPrice(new BigDecimal("299"));
        orderPosition.setQuantity(2);

        //When:
        orderPositionRepository.save(orderPosition);
        Long orderPositionId = orderPosition.getOrderPositionId();

        //Then:
        Assert.assertNotNull(orderPositionRepository.findById(orderPositionId));

        //Clean-up:
        orderPositionRepository.deleteById(orderPositionId);
    }


    @Test
    public void testUpdateOrderPosition() {
        //Given:
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setName("adidas");
        orderPosition.setPrice(new BigDecimal("299"));
        orderPosition.setQuantity(2);

        orderPositionRepository.save(orderPosition);
        Long orderPositionId = orderPosition.getOrderPositionId();
        OrderPosition fetchedPosition = orderPositionRepository.findById(orderPositionId).orElse(null);
        assert fetchedPosition != null;
        String initialName = fetchedPosition.getName();

        //When:
        fetchedPosition.setName("nike");
        orderPositionRepository.save(fetchedPosition);
        String updatedName = fetchedPosition.getName();

        //Then:
        Assert.assertNotEquals(initialName, updatedName);

        //Clean-up:
        orderPositionRepository.deleteById(orderPositionId);
    }


    @Test
    public void testDeleteOrderPosition() {
        //Given:
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setName("adidas_gold");
        orderPosition.setPrice(new BigDecimal("299"));
        orderPosition.setQuantity(2);

        orderPositionRepository.save(orderPosition);
        Long orderPositionId = orderPosition.getOrderPositionId();

        //Then:
        Assert.assertTrue(orderPositionRepository.existsById(orderPositionId));

        //When:
        orderPositionRepository.deleteById(orderPositionId);

        //Then:
        Assert.assertFalse(orderPositionRepository.existsById(orderPositionId));

    }


    @Test
    public void testDeleteOrderPositionVsOrder() {
        //Given:
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setName("adidas_gold");
        orderPosition.setPrice(new BigDecimal("299"));
        orderPosition.setQuantity(2);

        Order order = new Order();
        order.setCreationDate(LocalDate.now());
        order.setStatus("new");
        order.setPaymentMethod("wire");
        order.setDeliveryMethod("courier");

        orderPositionRepository.save(orderPosition);
        orderRepository.save(order);

        order.getOrderPositions().add(orderPosition);
        orderPosition.setOrder(order);

        orderPositionRepository.save(orderPosition);
        orderRepository.save(order);

        Long orderPositionId = orderPosition.getOrderPositionId();
        Long orderId = order.getOrderId();

        //When:
        orderPositionRepository.deleteById(orderPositionId);

        //Then: (assert that deleting OrderPosition does NOT delete the respective Order)
        Assert.assertFalse(orderPositionRepository.existsById(orderPositionId));
        Assert.assertTrue(orderRepository.existsById(orderId));

        //Clean-up:
        orderRepository.deleteById(orderId);
    }


    @Test
    public void testDeleteOrderVsOrderPosition() {
        //Given:
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setName("adidas_gold");
        orderPosition.setPrice(new BigDecimal("299"));
        orderPosition.setQuantity(2);

        Order order = new Order();
        order.setCreationDate(LocalDate.now());
        order.setStatus("new");
        order.setPaymentMethod("wire");
        order.setDeliveryMethod("courier");

        orderPositionRepository.save(orderPosition);
        orderRepository.save(order);

        order.getOrderPositions().add(orderPosition);
        orderPosition.setOrder(order);

        orderPositionRepository.save(orderPosition);
        orderRepository.save(order);

        Long orderPositionId = orderPosition.getOrderPositionId();
        Long orderId = order.getOrderId();

        //When:
        orderRepository.deleteById(orderId);

        //Then: (assert that deleting Order DOES delete the respective OrderPosition)
        Assert.assertFalse(orderRepository.existsById(orderId));
        Assert.assertFalse(orderPositionRepository.existsById(orderPositionId));

    }


}
