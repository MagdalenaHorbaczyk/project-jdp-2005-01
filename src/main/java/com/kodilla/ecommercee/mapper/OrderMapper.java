package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order (
            orderDto.getId(),
            orderDto.getOrderPositions(),
            orderDto.getCreationDate(),
                orderDto.getStatus(),
                orderDto.getUser(),
                orderDto.getDeliveryMethod(),
                orderDto.getPaymentMethod(),
                orderDto.getDeliveryAddress()
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderPositions(),
                order.getCreationDate(),
                order.getStatus(),
                order.getUser(),
                order.getDeliveryMethod(),
                order.getPaymentMethod(),
                order.getDeliveryAddress()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order>orders) {
        return orders.stream()
                .map(order -> new OrderDto(order.getOrderId(),order.getOrderPositions(),order.getCreationDate(),order.getStatus(),order.getUser(),order.getDeliveryMethod(),order.getPaymentMethod(),order.getDeliveryAddress()))
                .collect(Collectors.toList());
    }
}

