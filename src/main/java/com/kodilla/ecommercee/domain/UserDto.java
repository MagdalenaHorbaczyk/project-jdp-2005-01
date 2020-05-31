package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Long userId;
    private String name;
    private Integer status;
    private Integer userKey;
    private List<Object> orders; //List<Orders>

}
