package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.OrderPosition;
import com.kodilla.ecommercee.repository.OrderPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderPositionDbService {
    @Autowired
    OrderPositionRepository orderPositionRepository;

    public List<OrderPosition> getAllOrderPosition() {
        return orderPositionRepository.findAll();
    }

    public OrderPosition saveOrderPosition(final OrderPosition orderPosition) {
        return orderPositionRepository.save(orderPosition);
    }

    public Optional<OrderPosition> getOrderPosition(final Long id) {
        return orderPositionRepository.findById(id);
    }

    public void deleteOrderPosition(final Long id) {
        orderPositionRepository.deleteById(id);
    }
}


