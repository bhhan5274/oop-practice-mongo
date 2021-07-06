package io.github.bhhan.example.order.web;

import io.github.bhhan.example.order.usecase.OrderService;
import io.github.bhhan.example.order.usecase.dto.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Cart cart){
        return orderService.placeOrder(cart);
    }

    @PutMapping("/{orderId}/payed")
    @ResponseStatus(HttpStatus.OK)
    public void payOrder(@PathVariable String orderId){
        orderService.payOrder(orderId);
    }

    @PutMapping("/{orderId}/delivered")
    @ResponseStatus(HttpStatus.OK)
    public void deliverOrder(@PathVariable String orderId){
        orderService.deliverOrder(orderId);
    }
}
