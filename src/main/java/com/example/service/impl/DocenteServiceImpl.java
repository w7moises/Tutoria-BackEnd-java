package com.example.service.impl;

import com.example.entity.Docente;
import com.example.repository.DocenteRepository;
import com.example.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public Docente createDocente(Docente docente) {
        if (docente.getId() != null) {
            Optional<Docente> docenteRegistered = this.findById(docente.getId());
            if (docenteRegistered.isPresent()) {
                return docenteRegistered.get();
            }
        }
        docente.setStatus("CREATED");
        return docenteRepository.save(docente);
    }

    @Override
    public Docente updateDocente(Docente docente) {
        if (docente.getId() == null) return null;

        Optional<Docente> docenteRegistered = this.findById(docente.getId());
        if (!docenteRegistered.isPresent()) return null;

        Docente docenteUpdated = docenteRegistered.get();
        docenteUpdated.setNombre(docente.getNombre());
        docenteUpdated.setApellido(docente.getApellido());
        docenteUpdated.setCorreo(docente.getCorreo());
        docenteUpdated.setCosto(docente.getCosto());
        docenteUpdated.setDni(docente.getDni());
        docenteUpdated.setDomicilio(docente.getDomicilio());
        docenteUpdated.setDisponibilidad(docente.getDisponibilidad());
        docenteUpdated.setMembresia(docente.getMembresia());
        docenteUpdated.setNumeroCuenta(docente.getNumeroCuenta());
        docenteUpdated.setStatus("UPDATED");

        return docenteRepository.save(docenteUpdated);
    }

    @Override
    public Docente deleteDocente(Long id) {
        if (id == null) return null;

        Optional<Docente> docenteRegistered = this.findById(id);

        if (!docenteRegistered.isPresent()) return null;

        Docente docenteDeleted = docenteRegistered.get();
        docenteDeleted.setStatus("DELETED");

        return docenteRepository.save(docenteDeleted);
    }

    @Override
    public Optional<Docente> findById(Long id) {
        return docenteRepository.findById(id);
    }

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }
}
