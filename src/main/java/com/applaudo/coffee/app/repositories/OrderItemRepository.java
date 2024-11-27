package com.applaudo.coffee.app.repositories;

import com.applaudo.coffee.app.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {}
