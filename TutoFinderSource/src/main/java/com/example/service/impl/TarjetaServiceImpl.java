package com.example.service.impl;

import com.example.entity.Tarjeta;
import com.example.repository.TarjetaRepository;
import com.example.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public Tarjeta createTarjeta(Tarjeta tarjeta) {
        if (tarjeta.getId() != null) {
            Optional<Tarjeta> tarjetaRegistered = this.findById(tarjeta.getId());
            if (tarjetaRegistered.isPresent()) {
                return tarjetaRegistered.get();
            }
        }

        tarjeta.setStatus("CREATED");
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public Tarjeta updateTarjeta(Tarjeta tarjeta) {
        if (tarjeta.getId() == null) return null;

        Optional<Tarjeta> tarjetaRegistered = this.findById(tarjeta.getId());

        if (!tarjetaRegistered.isPresent()) return null;

        Tarjeta tarjetaUpdated = tarjetaRegistered.get();
        tarjetaUpdated.setFechaExpiracion(tarjeta.getFechaExpiracion());
        tarjetaUpdated.setNombrePoseedor(tarjeta.getNombrePoseedor());
        tarjetaUpdated.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
        tarjetaUpdated.setStatus("UPDATED");
        return tarjetaRepository.save(tarjetaUpdated);
    }

    @Override
    public Tarjeta deleteTarjeta(Long id) {
        if (id == null) return null;

        Optional<Tarjeta> tarjetaRegistered = this.findById(id);

        if (!tarjetaRegistered.isPresent()) return null;

        Tarjeta tarjetaDeleted = tarjetaRegistered.get();
        tarjetaDeleted.setStatus("DELETED");

        return tarjetaRepository.save(tarjetaDeleted);
    }

    @Override
    public Optional<Tarjeta> findById(Long id) {
        return tarjetaRepository.findById(id);
    }

    @Override
    public List<Tarjeta> findByAll() {
        return tarjetaRepository.findAll();
    }
}
