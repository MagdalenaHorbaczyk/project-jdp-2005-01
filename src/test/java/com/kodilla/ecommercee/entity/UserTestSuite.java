package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Test
    public void testUserSave() {
        //Given
        User anna = new User(1L,"Anna");
        User piotr = new User(2L, "Piotr");
        User tom = new User(3L, "Tomasz");
        User ada = new User(4L, "Ada");

        userRepository.save(anna);
        userRepository.save(piotr);
        userRepository.save(tom);
        userRepository.save(ada);

        Long idUserAnna = anna.getUserId();

        Cart cartUserAnna = new Cart();

        cartRepository.save(cartUserAnna);

        int cartIdUserAnna = cartUserAnna.getCartId();

        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        Long order1Id = order1.getOrderId();
        Long order2Id = order2.getOrderId();
        Long order3Id = order3.getOrderId();

        //anna.getOrders().add();





    }


}
