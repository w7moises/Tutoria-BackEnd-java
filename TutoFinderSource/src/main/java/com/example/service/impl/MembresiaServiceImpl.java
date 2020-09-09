package com.example.service.impl;

import com.example.entity.*;
import com.example.repository.MembresiaRepository;
import com.example.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaServiceImpl implements MembresiaService {
    @Autowired
    private MembresiaRepository membresiaRepository;

    @Override
    public Membresia createMembresia(Membresia membresia) {
        if (membresia.getId() != null) {
            Optional<Membresia> membresiaRegistered = this.findById(membresia.getId());
            if (membresiaRegistered.isPresent()) {
                return membresiaRegistered.get();
            }
        }

        membresia.setStatus("CREATED");
        return membresiaRepository.save(membresia);
    }

    @Override
    public Membresia updateMembresia(Membresia membresia) {
        if (membresia.getId() == null) return null;

        Optional<Membresia> membresiaRegistered = this.findById(membresia.getId());
        if (!membresiaRegistered.isPresent()) return null;
        Membresia membresiaUpdated = membresiaRegistered.get();
        membresiaUpdated.setDocentes(membresia.getDocentes());
        membresiaUpdated.setTarjeta(membresia.getTarjeta());
        membresiaUpdated.setFechaExpiracion(membresia.getFechaExpiracion());
        membresiaUpdated.setStatus("UPDATED");
        return membresiaRepository.save(membresiaUpdated);
    }

    @Override
    public Membresia deleteMembresia(Long id) {
        if (id == null) return null;
        Optional<Membresia> membresiaRegistered = this.findById(id);
        if (!membresiaRegistered.isPresent()) return null;
        Membresia membresiaDeleted = membresiaRegistered.get();
        membresiaDeleted.setStatus("DELETED");
        return membresiaRepository.save(membresiaDeleted);
    }

    @Override
    public Optional<Membresia> findById(Long id) {
        return membresiaRepository.findById(id);
    }

    @Override
    public List<Membresia> findAll() {
        return membresiaRepository.findAll();
    }

}
