package com.applaudo.coffee.app.repositories;

import com.applaudo.coffee.app.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CoffeeRepository extends JpaRepository<Coffee, UUID> {
    List<Coffee> findByNameContainingIgnoreCase(String name);
}
