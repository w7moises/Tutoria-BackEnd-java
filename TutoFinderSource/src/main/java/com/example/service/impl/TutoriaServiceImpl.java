package com.example.service.impl;

import com.example.entity.*;
import com.example.repository.TutoriaRepository;
import com.example.service.TutoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutoriaServiceImpl implements TutoriaService {

    @Autowired
    private TutoriaRepository tutoriaRepository;

    @Override
    public Tutoria createTutoria(Tutoria tutoria) {
        if (tutoria.getId() != null) {
            Optional<Tutoria> tutoriaRegistered = this.findById(tutoria.getId());
            if (tutoriaRegistered.isPresent()) {
                return tutoriaRegistered.get();
            }
        }

        tutoria.setStatus("CREATED");
        return tutoriaRepository.save(tutoria);
    }

    @Override
    public Tutoria updateTutoria(Tutoria tutoria) {
        if (tutoria.getId() == null) return null;

        Optional<Tutoria> tutoriaRegistered = this.findById(tutoria.getId());
        if (!tutoriaRegistered.isPresent()) return null;
        Tutoria tutoriaUpdated = tutoriaRegistered.get();
        tutoriaUpdated.setDescripcionTutoria(tutoria.getDescripcionTutoria());
        tutoriaUpdated.setCantidadMinutos(tutoria.getCantidadMinutos());
        tutoriaUpdated.setAlumnos(tutoria.getAlumnos());
        tutoriaUpdated.setCurso(tutoria.getCurso());
        tutoriaUpdated.setPago(tutoria.getPago());
        tutoriaUpdated.setStatus("UPDATED");
        return tutoriaRepository.save(tutoriaUpdated);
    }

    @Override
    public Tutoria deleteTutoria(Long id) {
        if (id == null) return null;
        Optional<Tutoria> tutoriaRegistered = this.findById(id);
        if (!tutoriaRegistered.isPresent()) return null;
        Tutoria tutoriaDeleted = tutoriaRegistered.get();
        tutoriaDeleted.setStatus("DELETED");
        return tutoriaRepository.save(tutoriaDeleted);
    }

    @Override
    public Optional<Tutoria> findById(Long id) {
        return tutoriaRepository.findById(id);
    }

    @Override
    public List<Tutoria> findAll() {
        return tutoriaRepository.findAll();
    }

    @Override
    public List<Tutoria> findByCurso(Curso curso) {
        return tutoriaRepository.findByCurso(curso);
    }

    @Override
    public List<Tutoria> findByDocente(Docente docente) {
        return tutoriaRepository.findByDocente(docente);
    }

    @Override
    public List<Tutoria> findByCursoAndDocente(Curso curso, Docente docente) {
        return tutoriaRepository.findByCursoAndDocente(curso, docente);
    }

    @Override
    public List<Tutoria> findByNombreCurso(String searchQuery) {
        return tutoriaRepository.findByNombreCurso(searchQuery);
    }

    @Override
    public List<Tutoria> findByNombreDocente(String searchQuery) {
        return tutoriaRepository.findByNombreDocente(searchQuery);
    }

    @Override
    public List<Tutoria> findByCostoBetween(Float minValue, Float maxValue) {
        return tutoriaRepository.findByCostoBetween(minValue, maxValue);
    }

    @Override
    public List<Tutoria> findByNombreCursoAndNombreDocente(String searchCursoQuery, String searchDocenteQuery) {
        return tutoriaRepository.findByNombreCursoAndNombreDocente(searchCursoQuery, searchDocenteQuery);
    }

    @Override
    public List<Tutoria> findByNombreCursoAndCostoBetween(String searchQuery, Float minValue, Float maxValue) {
        return tutoriaRepository.findByNombreCursoAndCostoBetween(searchQuery, minValue, maxValue);
    }

    @Override
    public List<Tutoria> findByNombreDocenteAndCostoBetween(String searchQuery, Float minValue, Float maxValue) {
        return tutoriaRepository.findByNombreCursoAndCostoBetween(searchQuery, minValue, maxValue);
    }

    @Override
    public List<Tutoria> findByNombreCursoAndNombreDocenteAndCostoBetween(String searchCursoQuery, String searchDocenteQuery, Float minValue, Float maxValue) {
        return tutoriaRepository.findByNombreCursoAndNombreDocenteAndCostoBetween(searchCursoQuery, searchDocenteQuery, minValue, maxValue);
    }
}
