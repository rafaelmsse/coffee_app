package com.applaudo.coffee.app.repositories;

import com.applaudo.coffee.app.models.Additional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdditionalRepository extends JpaRepository<Additional, UUID> {}

