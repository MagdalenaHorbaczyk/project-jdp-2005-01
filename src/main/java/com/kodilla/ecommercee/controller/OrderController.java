package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.entity.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class OrderController {
    //DummyData -start
    private List<OrderPosition> orderPositions = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    private Cart dummyCart = new Cart(1L, products);
    private User dummyUser = new User(99L, "TempUser", "1", orders, dummyCart, UserStatus.TO_ACTIVATE);
    private DeliveryAddress dummyDeliveryAddress = new DeliveryAddress(
            1L, "name", "country", "city", "street", "buildingNumber", 3, "postcode", 123456789, new ArrayList<>());

    private OrderDto dummyOrderDto1 = new OrderDto(
            7L, orderPositions, LocalDate.of(2020, 6, 19), "queued", dummyUser, "Courier", "PayPal", dummyDeliveryAddress);
    private OrderDto dummyOrderDto2 = new OrderDto(
            9L, orderPositions, LocalDate.of(2020, 5, 23), "end", dummyUser, "By Hand", "Cash", dummyDeliveryAddress);
    private OrderDto dummyOrderDto3 = new OrderDto(
            3L, orderPositions, LocalDate.of(2012, 7, 3), "end", dummyUser, "By Hand", "PayPal", dummyDeliveryAddress);
    //DummyData -end

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) {
        return dummyOrderDto3;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        List<OrderDto> dummyOrderDtoList = new ArrayList<>();
        dummyOrderDtoList.add(dummyOrderDto1);
        dummyOrderDtoList.add(dummyOrderDto2);
        return dummyOrderDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @RequestParam Long orderId) {
        return dummyOrderDto1;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder", consumes = APPLICATION_JSON_VALUE)
    public void deleteOrder(@RequestParam Long orderId) {
    }
}
