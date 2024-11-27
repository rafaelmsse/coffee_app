package com.applaudo.coffee.app.controllers;

import com.applaudo.coffee.app.models.Additional;
import com.applaudo.coffee.app.models.Coffee;
import com.applaudo.coffee.app.services.AdditionalService;
import com.applaudo.coffee.app.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private AdditionalService additionalService;


    @GetMapping
    public String getAllCoffees(Model model) {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        model.addAttribute("coffees", coffees);
        return "coffees/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("coffeeDto", new CoffeeDto());
        List<Additional> additionals = additionalService.getAllAdditionals();
        model.addAttribute("additionals", additionals);
        return "coffees/create";
    }

    @PostMapping
    public String registerCoffee(@ModelAttribute("coffeeDto") CoffeeDto coffeeDto) {
        Coffee coffee = new Coffee();
        coffee.setName(coffeeDto.getName());
        coffee.setDescription(coffeeDto.getDescription());
        coffee.setEnabled(true);

        List<Additional> selectedAdditionals = new ArrayList<Additional>();
        if (coffeeDto.getAdditionalIds() != null) {
            for (UUID additionalId : coffeeDto.getAdditionalIds()) {
                Additional additional = additionalService.findById(additionalId);
                selectedAdditionals.add(additional);
            }
        }
        coffee.setAdditionals(selectedAdditionals);
        BigDecimal finalPrice = coffeeDto.getPrice();
        for (Additional additional : coffee.getAdditionals()) {
            BigDecimal adjustedCost = additional.getCost().multiply(additional.getCombineCost());
            finalPrice = finalPrice.add(adjustedCost);
        }

        coffee.setPrice(finalPrice);

        selectedAdditionals.sort(Comparator.comparing(Additional::getCost).reversed()
                .thenComparing(Additional::getDescription));

        StringBuilder nameBuilder = new StringBuilder();
        for (Additional additional : selectedAdditionals) {
            nameBuilder.append(additional.getDescription()).append(" ");
        }
        nameBuilder.append(coffee.getName());
        coffee.setName(nameBuilder.toString().trim());
        coffeeService.registerCoffee(coffee);
        return "redirect:/coffees";
    }

    @GetMapping("/search")
    public String searchCoffees(@RequestParam String query, Model model) {
        List<Coffee> coffees = coffeeService.searchCoffees(query);
        model.addAttribute("coffees", coffees);
        model.addAttribute("query", query);
        return "coffees/list";
    }

    public static class CoffeeDto {
        private String name;
        private String description;
        private BigDecimal price;
        private List<UUID> additionalIds;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public List<UUID> getAdditionalIds() {
            return additionalIds;
        }

        public void setAdditionalIds(List<UUID> additionalIds) {
            this.additionalIds = additionalIds;
        }
    }
}
