package com.applaudo.coffee.app.services;

import com.applaudo.coffee.app.models.Additional;
import com.applaudo.coffee.app.models.Coffee;
import com.applaudo.coffee.app.models.Order;
import com.applaudo.coffee.app.models.OrderItem;
import com.applaudo.coffee.app.repositories.AdditionalRepository;
import com.applaudo.coffee.app.repositories.CoffeeRepository;
import com.applaudo.coffee.app.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private AdditionalRepository additionalRepository;

    public Order createOrder(String description, List<UUID> itemRequests) {
        Order order = new Order();
        order.setDescription(description);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (UUID itemRequest : itemRequests) {
            OrderItem item = new OrderItem();
            item.setOrder(order);

            Coffee coffee = coffeeRepository.findById(itemRequest)
                    .orElseThrow(() -> new RuntimeException("Coffee not found"));
            item.setCoffee(coffee);

            BigDecimal coffeePrice = coffeeService.getCurrentPrice(coffee);

            item.setCalculatedPrice(coffeePrice);

            items.add(item);
            total = total.add(coffeePrice);        }

        order.setItems(items);
        order.setTotalItems(items.size());
        order.setTotal(total);

        return orderRepository.save(order);
    }

    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
