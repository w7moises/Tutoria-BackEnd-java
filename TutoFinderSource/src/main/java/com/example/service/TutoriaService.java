package com.example.service;

import com.example.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TutoriaService {
    Tutoria createTutoria(Tutoria tutoria);
    Tutoria updateTutoria(Tutoria tutoria);
    Tutoria deleteTutoria(Long id);

    Optional<Tutoria> findById(Long id);
    List<Tutoria> findAll();

    List<Tutoria> findByCurso(Curso curso);
    List<Tutoria> findByDocente(Docente docente);

    List<Tutoria> findByCursoAndDocente(Curso curso, Docente docente);

    List<Tutoria> findByNombreCurso(String searchQuery);
    List<Tutoria> findByNombreDocente(String searchQuery);
    List<Tutoria> findByCostoBetween(Float minValue, Float maxValue);

    List<Tutoria> findByNombreCursoAndNombreDocente(String searchCursoQuery, String searchDocenteQuery);
    List<Tutoria> findByNombreCursoAndCostoBetween(String searchQuery, Float minValue, Float maxValue);
    List<Tutoria> findByNombreDocenteAndCostoBetween(String searchQuery, Float minValue, Float maxValue);

    List<Tutoria> findByNombreCursoAndNombreDocenteAndCostoBetween(String searchCursoQuery, String searchDocenteQuery, Float minValue, Float maxValue);
}
