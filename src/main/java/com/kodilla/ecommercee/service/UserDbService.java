package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.*;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDbService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(final User user){
        return userRepository.save(user);
    }

    public Optional<User> getUser(final Long userId){
        return userRepository.findById(userId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean isExist(Long userId){
        return userRepository.existsById(userId);
    }

    public void setUserCart(Long userId, Long cartId) throws UserNotFoundException, CartNotFoundException{
        User userCart = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cartUser = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        userCart.setCart(cartUser);
        userRepository.save(userCart);
    }

    public void setOrdersToUser(Long userId, Long orderId) throws UserNotFoundException, OrderNotFoundException {
        User userOrder = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Order orderToUser = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        userOrder.getOrders().add(orderToUser);
        orderToUser.setUser(userOrder);
        userRepository.save(userOrder);
        orderRepository.save(orderToUser);
    }
}
