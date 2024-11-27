package com.applaudo.coffee.app.repositories;

import com.applaudo.coffee.app.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {}
