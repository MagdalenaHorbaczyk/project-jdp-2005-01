package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long userId;
    private String name;
    private Integer status;
    private Integer userKey;
    private List<OrderDto> orders;
    private Long cartId;
    private String deliveryAdresId;

}
