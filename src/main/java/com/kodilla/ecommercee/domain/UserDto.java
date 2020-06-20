package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.UserStatus;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long userId;
    private String username;
    private String userKey;
    private List<Order> orders;
    private Cart cartId;
    private UserStatus status;
}