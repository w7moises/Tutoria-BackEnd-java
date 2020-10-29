package com.example.service.impl;

import com.example.entity.Padre;
import com.example.entity.Pago;
import com.example.entity.Tarjeta;
import com.example.repository.PagoRepository;
import com.example.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public Pago createPago(Pago pago) {
        if (pago.getId() != null) {
            Optional<Pago> pagoRegistered = this.findById(pago.getId());
            if (pagoRegistered.isPresent()) {
                return pagoRegistered.get();
            }
        }

        pago.setStatus("CREATED");
        return pagoRepository.save(pago);
    }

    @Override
    public Pago updatePago(Pago pago) {
        if (pago.getId() == null) return null;

        Optional<Pago> pagoRegistered = this.findById(pago.getId());
        if (!pagoRegistered.isPresent()) return null;
        Pago pagoUpdated = pagoRegistered.get();
        pagoUpdated.setDescripcionPago(pago.getDescripcionPago());
        pagoUpdated.setPadre(pago.getPadre());
        pagoUpdated.setTarjeta(pago.getTarjeta());
        pagoUpdated.setStatus("UPDATED");
        return pagoRepository.save(pagoUpdated);
    }

    @Override
    public Pago deletePago(Long id) {
        if (id == null) return null;
        Optional<Pago> pagoRegistered = this.findById(id);
        if (!pagoRegistered.isPresent()) return null;
        Pago pagoDeleted = pagoRegistered.get();
        pagoDeleted.setStatus("DELETED");
        return pagoRepository.save(pagoDeleted);
    }

    @Override
    public Optional<Pago> findById(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public List<Pago> findByPadre(Padre padre) {
        return pagoRepository.findByPadre(padre);
    }

    @Override
    public List<Pago> findByTarjeta(Tarjeta tarjeta) {
        return pagoRepository.findByTarjeta(tarjeta);
    }

    @Override
    public List<Pago> findByPadreAndTarjeta(Padre padre, Tarjeta tarjeta) {
        return pagoRepository.findByPadreAndTarjeta(padre, tarjeta);
    }
}
