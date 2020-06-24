package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entity.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getUserKey(),
                userDto.getOrders(),
                userDto.getCart(),
                userDto.getStatus());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getUserKey(),
                user.getOrders(),
                user.getCart(),
                user.getStatus());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(u.getUserId(), u.getUsername(), u.getUserKey(), u.getOrders(),
                        u.getCart(), u.getStatus()))
                .collect(Collectors.toList());
    }
}

