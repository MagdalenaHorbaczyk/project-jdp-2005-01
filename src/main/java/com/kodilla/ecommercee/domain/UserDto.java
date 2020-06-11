package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.UserStatus;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long userId;
    private String username;
    private String userKey;
    private List<OrderDto> orders;
    private int cartId;
    private UserStatus status;
}