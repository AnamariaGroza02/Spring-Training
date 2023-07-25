package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.msg.learning.shop.domain.PlacedOrder;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.OrderService;

@Controller
@RequestMapping("/shop/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        PlacedOrder orderEntity = OrderMapper.INSTANCE.toOrder(orderDto);
        PlacedOrder order = orderService.createOrder(orderEntity);

        return new ResponseEntity<>(OrderMapper.INSTANCE.toOrderDto(order), HttpStatus.CREATED);
    }
}
