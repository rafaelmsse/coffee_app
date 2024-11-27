package com.applaudo.coffee.app.controllers;

import com.applaudo.coffee.app.models.Order;
import com.applaudo.coffee.app.services.CoffeeService;
import com.applaudo.coffee.app.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("orderDto", new OrderDto());
        model.addAttribute("coffees", coffeeService.getAllCoffees());
        return "orders/create";
    }

    @PostMapping
    public String createOrder(@ModelAttribute OrderDto orderDto) {
        orderService.createOrder(orderDto.getDescription(), orderDto.getItems());
        return "redirect:/orders";
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable UUID orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "orders/view";
    }

    public static class OrderDto {
        private String description;
        private List<UUID> items;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<UUID> getItems() {
            return items;
        }

        public void setItems(List<UUID> items) {
            this.items = items;
        }
    }
}
