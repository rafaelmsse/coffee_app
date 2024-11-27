package com.applaudo.coffee.app.repositories;

import com.applaudo.coffee.app.models.Coffee;
import com.applaudo.coffee.app.models.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, UUID> {
    PriceHistory findFirstByCoffeeAndDateToIsNullOrderByDateFromDesc(Coffee coffee);
}

