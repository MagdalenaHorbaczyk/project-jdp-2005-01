package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.UserStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long userId;
    private String userName;
    private String userKey;
    private String deliveryAddressId;
    private List<OrderDto> orders;
    private Long cartId;
    private UserStatus status;
}
