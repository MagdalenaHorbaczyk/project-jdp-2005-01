package com.kodilla.ecommercee.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {

    @Id
    @NotNull
    @GeneratedValue
    @Column (name = "ORDER_ID")
    private Long orderId;

    @OneToMany(
            mappedBy = "order",
            targetEntity = OrderPosition.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    @Column(name = "ORDER_POSITION")
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    @NotNull
    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    private User user;

    @NotNull
    @Column(name = "DELIVERY_METHOD")
    private String deliveryMethod;

    @NotNull
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @ManyToOne
    private DeliveryAddress deliveryAddress;


    @Override
    public String toString() {
        return "Order details: " + "\n" +
                "orderId = " + orderId + "\n" +
                "creationDate = " + creationDate + "\n" +
                "status = " + status + "\n" +
                "deliveryMethod = " + deliveryMethod + "\n" +
                "paymentMethod = " + paymentMethod;
    }
}

