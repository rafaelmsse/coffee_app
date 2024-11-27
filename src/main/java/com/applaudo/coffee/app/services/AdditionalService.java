package com.applaudo.coffee.app.services;

import com.applaudo.coffee.app.models.Additional;
import com.applaudo.coffee.app.repositories.AdditionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdditionalService {

    @Autowired
    private AdditionalRepository additionalRepository;

    public Additional registerAdditional(Additional additional) {
        return additionalRepository.save(additional);
    }

    public List<Additional> getAllAdditionals() {
        return additionalRepository.findAll();
    }

    public Additional findById(UUID additionalId) {
        return additionalRepository.findById(additionalId)
                .orElseThrow(() -> new RuntimeException("Additional not found with ID: " + additionalId));
    }
}
