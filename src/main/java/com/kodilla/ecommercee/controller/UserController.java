package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entity.UserStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    private List<OrderDto> orderDtoList = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) {
        return new UserDto(99L, "TempUser", "1", orderDtoList, 10, UserStatus.TO_ACTIVATE);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        UserDto dummyUserDtoA = new UserDto(91L, "Adam_D", "1", orderDtoList, 1, UserStatus.ACTIVE);
        UserDto dummyUserDtoB = new UserDto(92L, "Bartek_D", "0", orderDtoList, 12, UserStatus.TO_ACTIVATE);
        List<UserDto> dummyUserDtoList = new ArrayList<>();
        dummyUserDtoList.add(dummyUserDtoA);
        dummyUserDtoList.add(dummyUserDtoB);
        return dummyUserDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public void createUser(@RequestBody UserDto userDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return new UserDto(93L, "Czarek_D", "0", orderDtoList, 2, UserStatus.TO_ACTIVATE);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
    }
}