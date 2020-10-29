package com.example.service;

import com.example.entity.Padre;

import java.util.List;
import java.util.Optional;

public interface PadreService {
    Padre createPadre(Padre padre);
    Padre updatePadre(Padre padre);
    Padre deletePadre(Long id);

    Optional<Padre> findById(Long id);
    List<Padre> findAll();

    Padre findByDni(String dni);
}
