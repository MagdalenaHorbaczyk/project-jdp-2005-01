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
        Order order2 = new Order();
        Order order3 = new Order();

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













