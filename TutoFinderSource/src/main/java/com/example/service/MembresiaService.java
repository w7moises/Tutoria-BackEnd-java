package com.example.service;

import com.example.entity.*;

import java.util.List;
import java.util.Optional;

public interface MembresiaService {
    Membresia createMembresia(Membresia membresia);
    Membresia updateMembresia(Membresia membresia);
    Membresia deleteMembresia(Long id);

    Optional<Membresia> findById(Long id);
    List<Membresia> findAll();
}
