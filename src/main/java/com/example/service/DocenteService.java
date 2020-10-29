package com.example.service;

import com.example.entity.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {
    Docente createDocente(Docente docente);
    Docente updateDocente(Docente docente);
    Docente deleteDocente(Long id);

    Optional<Docente> findById(Long id);
    List<Docente> findAll();
}
