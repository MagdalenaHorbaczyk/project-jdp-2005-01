package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.DeliveryAddress;
import com.kodilla.ecommercee.entity.OrderPosition;
import com.kodilla.ecommercee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private List<OrderPosition> orderPositions;
    private LocalDate creationDate;
    private String status;
    private User user;
    private String deliveryMethod;
    private String paymentMethod;
    private DeliveryAddress deliveryAddress;
}
