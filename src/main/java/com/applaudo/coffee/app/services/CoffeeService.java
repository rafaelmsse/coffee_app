package com.applaudo.coffee.app.services;

import com.applaudo.coffee.app.models.Additional;
import com.applaudo.coffee.app.models.Coffee;
import com.applaudo.coffee.app.models.PriceHistory;
import com.applaudo.coffee.app.repositories.CoffeeRepository;
import com.applaudo.coffee.app.repositories.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    public Coffee registerCoffee(Coffee coffee) {
        coffee = coffeeRepository.save(coffee);
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setCoffee(coffee);
        priceHistory.setPrice(coffee.getPrice());
        priceHistory.setDateFrom(LocalDateTime.now());
        priceHistoryRepository.save(priceHistory);
        return coffee;
    }

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public List<Coffee> searchCoffees(String query) {
        return coffeeRepository.findByNameContainingIgnoreCase(query);
    }

    public BigDecimal getCurrentPrice(Coffee coffee) {
        PriceHistory currentPrice = priceHistoryRepository.findFirstByCoffeeAndDateToIsNullOrderByDateFromDesc(coffee);
        if (currentPrice != null) {
            return currentPrice.getPrice();
        }
        throw new RuntimeException("No current price available for coffee");
    }
}
