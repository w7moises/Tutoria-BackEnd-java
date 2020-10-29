package com.example.repository;

import com.example.entity.Padre;
import com.example.entity.Pago;
import com.example.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago,Long> {
    List<Pago> findByPadre(Padre padre);
    List<Pago> findByTarjeta(Tarjeta tarjeta);
    List<Pago> findByPadreAndTarjeta(Padre padre, Tarjeta tarjeta);
}
