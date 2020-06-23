package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.OrderPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPositionDto {
    private Long orderPositionId;
    private String name;
    private BigDecimal price;
    private int quantity;
    private Order order;

}

