package com.example.service;

import com.example.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    Curso createCurso(Curso curso);
    Curso updateCurso(Curso curso);
    Curso deleteCurso(Long id);

    Optional<Curso> findById(Long id);
    List<Curso> findAll();
}
