package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderPositionDto;
import com.kodilla.ecommercee.entity.OrderPosition;
import org.springframework.stereotype.Component;

@Component
public class OrderPositionMapper {
    public OrderPosition mapToOrderPosition(final OrderPositionDto orderPositionDto) {
        return new OrderPosition (
                orderPositionDto.getOrderPositionId(),
                orderPositionDto.getName(),
                orderPositionDto.getPrice(),
                orderPositionDto.getQuantity(),
                orderPositionDto.getOrder()

        );

    }
}
