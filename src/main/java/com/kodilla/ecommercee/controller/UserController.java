package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    private List<Object> tempObjects = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) {
        return new UserDto(99L, "TempUser", 1, 9990, tempObjects);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        UserDto dummyUserDtoA = new UserDto(91L, "Adam_D", 1, 9991, tempObjects);
        UserDto dummyUserDtoB = new UserDto(92L, "Bartek_D", 0, 9992, tempObjects);
        List<UserDto> dummyUserDtoList = new ArrayList<>();
        dummyUserDtoList.add(dummyUserDtoA);
        dummyUserDtoList.add(dummyUserDtoB);
        return dummyUserDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public void createUser(@RequestParam UserDto userDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public UserDto updateUser(@RequestParam Long userId){
        return new UserDto(93L, "Czarek_D", 0, 9993, tempObjects);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
    }
}