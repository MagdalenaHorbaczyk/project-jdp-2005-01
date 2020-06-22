package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDbService userDbService;

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userDbService.findAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }
    @RequestMapping (method = RequestMethod.PUT, value = "updateUser", consumes = APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userDbService.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException {
        if (userDbService.isExist(userId)) {
            userDbService.deleteUser(userId);
        } else {
            throw new UserNotFoundException();
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "userStatus")
    public void isExist(@RequestParam Long userId){
        userDbService.isExist(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "setCart")
    public void setCart(@RequestParam Long userId, @RequestParam Long cartId) throws UserNotFoundException, CartNotFoundException {
        userDbService.setUserCart(userId, cartId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "setOrderList")
    public void setOrder(@RequestParam Long userId, @RequestParam Long orderId) throws UserNotFoundException, OrderNotFoundException {
        userDbService.setOrdersToUser(userId, orderId);
    }
}