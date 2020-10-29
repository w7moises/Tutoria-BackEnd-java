package com.example.service;

import com.example.entity.Padre;
import com.example.entity.Pago;
import com.example.entity.Tarjeta;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    Pago createPago(Pago pago);
    Pago updatePago(Pago pago);
    Pago deletePago(Long id);

    Optional<Pago> findById(Long id);
    List<Pago> findAll();

    List<Pago> findByPadre(Padre padre);
    List<Pago> findByTarjeta(Tarjeta tarjeta);
    List<Pago> findByPadreAndTarjeta(Padre padre, Tarjeta tarjeta);
}
