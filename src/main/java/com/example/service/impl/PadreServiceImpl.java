package com.example.service.impl;

import com.example.entity.Padre;
import com.example.repository.PadreRepository;
import com.example.service.PadreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PadreServiceImpl implements PadreService {
    @Autowired
    private PadreRepository padreRepository;

    @Override
    public Padre createPadre(Padre padre) {
        if (padre.getId() != null) {
            Optional<Padre> padreRegistered = this.findById(padre.getId());
            if (padreRegistered.isPresent()) {
                return padreRegistered.get();
            }
        }
        padre.setStatus("CREATED");
        return padreRepository.save(padre);
    }

    @Override
    public Padre updatePadre(Padre padre) {
        if (padre.getId() == null) return null;

        Optional<Padre> padreRegistered = this.findById(padre.getId());

        if (!padreRegistered.isPresent()) return null;

        Padre padreUpdated = padreRegistered.get();

        padreUpdated.setNombre(padre.getNombre());
        padreUpdated.setApellido(padre.getApellido());
        padreUpdated.setCorreo(padre.getCorreo());
        padreUpdated.setDni(padre.getDni());
        padreUpdated.setStatus("UPDATED");

        return padreRepository.save(padreUpdated);
    }

    @Override
    public Padre deletePadre(Long id) {
        if (id == null) return null;

        Optional<Padre> padreRegistered = this.findById(id);

        if (!padreRegistered.isPresent()) return null;

        Padre padreDeleted = padreRegistered.get();
        padreDeleted.setStatus("DELETED");

        return padreRepository.save(padreDeleted);
    }

    @Override
    public Padre findByDni(String dni) {
        return padreRepository.findByDni(dni);
    }

    @Override
    public Optional<Padre> findById(Long id) {
        return padreRepository.findById(id);
    }

    @Override
    public List<Padre> findAll() {
        return padreRepository.findAll();
    }
}
