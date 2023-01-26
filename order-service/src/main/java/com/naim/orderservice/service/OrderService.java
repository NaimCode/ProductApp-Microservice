package com.naim.orderservice.service;

import com.naim.orderservice.Model.Order;
import com.naim.orderservice.Model.OrderItem;
import com.naim.orderservice.dto.OrderRequest;
import com.naim.orderservice.repository.OrderRepository;
import com.naim.productservice.Model.Product;
import com.naim.productservice.dto.ProductRequest;
import com.naim.productservice.dto.ProductResponse;
import com.naim.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        log.info("Order request received {}", orderRequest);
        Order order = new Order();
        order.setNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderRequest.getOrderItemsDto().stream().map(
                e -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(e.getQuantity());
                    orderItem.setSkuCode(e.getSkuCode());
                    orderItem.setQuantity(e.getQuantity());
                    return orderItem;
                }
        ).toList());
        orderRepository.save(order);
    }

}
