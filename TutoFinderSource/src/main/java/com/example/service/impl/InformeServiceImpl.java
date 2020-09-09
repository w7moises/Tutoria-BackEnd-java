package com.example.service.impl;

import com.example.entity.Informe;
import com.example.repository.InformeRepository;
import com.example.service.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformeServiceImpl implements InformeService {

    @Autowired
    private InformeRepository informeRepository;

    @Override
    public Informe createInforme(Informe informe) {
        if (informe.getId() != null) {
            Optional<Informe> informeRegistered = this.findById(informe.getId());
            if (informeRegistered.isPresent()) {
                return informeRegistered.get();
            }
        }
        informe.setStatus("CREATED");
        return informeRepository.save(informe);
    }

    @Override
    public Informe updateInforme(Informe informe) {
        if (informe.getId() == null) return null;

        Optional<Informe> informeRegistered = this.findById(informe.getId());
        if (!informeRegistered.isPresent()) return null;
        Informe informeUpdated = informeRegistered.get();
        informeUpdated.setDescripcionInforme(informe.getDescripcionInforme());
        informeUpdated.setTutorias(informe.getTutorias());
        informeUpdated.setStatus("UPDATED");
        return informeRepository.save(informeUpdated);
    }

    @Override
    public Informe deleteInforme(Long id) {
        if (id == null) return null;

        Optional<Informe> informeRegistered = this.findById(id);
        if (!informeRegistered.isPresent()) return null;

        Informe informeDeleted = informeRegistered.get();
        informeDeleted.setStatus("DELETED");

        return informeRepository.save(informeDeleted);
    }

    @Override
    public Optional<Informe> findById(Long id) {
        return informeRepository.findById(id);
    }

    @Override
    public List<Informe> findAll() {
        return informeRepository.findAll();
    }
}
