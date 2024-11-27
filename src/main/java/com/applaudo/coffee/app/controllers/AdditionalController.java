package com.applaudo.coffee.app.controllers;

import com.applaudo.coffee.app.models.Additional;
import com.applaudo.coffee.app.services.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/additionals")
public class AdditionalController {

    @Autowired
    private AdditionalService additionalService;

    @GetMapping
    public String getAllAdditionals(Model model) {
        List<Additional> additionals = additionalService.getAllAdditionals();
        model.addAttribute("additionals", additionals);
        return "additionals/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("additional", new Additional());
        return "additionals/create";
    }

    @PostMapping
    public String registerAdditional(@ModelAttribute Additional additional) {
        additionalService.registerAdditional(additional);
        return "redirect:/additionals";
    }
}
