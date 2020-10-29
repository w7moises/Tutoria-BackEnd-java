package com.example.service;

import com.example.entity.Tarjeta;

import java.util.List;
import java.util.Optional;

public interface TarjetaService {

    Tarjeta createTarjeta(Tarjeta tarjeta);
    Tarjeta updateTarjeta(Tarjeta tarjeta);
    Tarjeta deleteTarjeta(Long id);

    Optional<Tarjeta> findById(Long id);
    List<Tarjeta> findByAll();
}
