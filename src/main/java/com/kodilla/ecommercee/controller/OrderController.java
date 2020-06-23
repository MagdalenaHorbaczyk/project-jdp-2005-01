package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderPositionDto;
import com.kodilla.ecommercee.entity.OrderPosition;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.OrderPositionMapper;
import com.kodilla.ecommercee.repository.OrderPositionRepository;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.OrderPositionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderPositionMapper orderPositionMapper;

    @Autowired
    OrderDbService orderDbService;

    @Autowired
    OrderPositionDbService orderPositionDbService;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderDbService.getAllOrders());
    }
    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) {
        return orderMapper.mapToOrderDto(orderDbService.getOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder", consumes = APPLICATION_JSON_VALUE)
    public void deleteOrder(@RequestParam Long orderId) {
        orderDbService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrderPosition", consumes = APPLICATION_JSON_VALUE)
    public void deleteOrderPosition(@RequestParam Long id) {
        orderPositionDbService.deleteOrderPosition(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }


    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderDbService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrderPosition", consumes = APPLICATION_JSON_VALUE)
    public void createOrderPosition(@RequestBody OrderPositionDto orderPositionDto) {
        orderPositionDbService.saveOrderPosition(orderPositionMapper.mapToOrderPosition(orderPositionDto));
    }

}
