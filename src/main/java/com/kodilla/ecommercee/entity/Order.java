package com.kodilla.ecommercee.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id
    @NotNull
    @GeneratedValue
    private Long orderId;

    @OneToMany(
            mappedBy = "order",
            targetEntity = OrderPosition.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private String status;

    @ManyToOne
    private User user;

    @NotNull
    private String deliveryMethod;

    @NotNull
    private String paymentMethod;

    @ManyToOne
    private DeliveryAddress deliveryAddress;

}

