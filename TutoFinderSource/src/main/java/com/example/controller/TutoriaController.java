package com.example.controller;

import com.example.entity.*;
import com.example.service.TutoriaService;
import com.example.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tutorias")
public class TutoriaController {

    @Autowired
    private TutoriaService tutoriaService;

    @GetMapping
    private ResponseEntity<?> getAll(
            @RequestParam(value = "cursoId", required = false) Long cursoId,
            @RequestParam(value = "pagoId", required = false) Long pagoId,
            @RequestParam(value = "docenteId", required = false) Long docenteId) {
        List<Tutoria> tutorias = new ArrayList<>();
        if (cursoId != null && docenteId != null) {
            tutorias = tutoriaService.findByCursoAndDocente(Curso.builder().id(cursoId).build(), Docente.builder().id(pagoId).build());
        } else if (cursoId != null) {
            tutorias = tutoriaService.findByCurso(Curso.builder().id(cursoId).build());
        } else if (docenteId != null) {
            tutorias = tutoriaService.findByDocente(Docente.builder().id(cursoId).build());
        } else {
            tutorias = tutoriaService.findAll();
            if (tutorias.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(tutorias);
    }

    @GetMapping("/filters")
    private ResponseEntity<?> getFiltered(
            @RequestParam(value = "searchQueryDocente", required = false) String searchQueryDocente,
            @RequestParam(value = "searchQueryCurso", required = false) String searchQueryCurso,
            @RequestParam(value = "minValue", required = false) Float minValue,
            @RequestParam(value = "maxValue", required = false) Float maxValue){
        List<Tutoria> tutorias = new ArrayList<>();
        if (searchQueryDocente != null && searchQueryCurso != null && minValue != null && maxValue != null) {
            tutorias = tutoriaService.findByNombreCursoAndNombreDocenteAndCostoBetween(searchQueryCurso, searchQueryDocente, minValue, maxValue);
        } else if (searchQueryDocente != null && searchQueryCurso != null) {
            tutorias = tutoriaService.findByNombreCursoAndNombreDocente(searchQueryCurso, searchQueryDocente);
        } else if (searchQueryDocente != null && minValue != null && maxValue != null) {
            tutorias = tutoriaService.findByNombreDocenteAndCostoBetween(searchQueryDocente, minValue, maxValue);
        } else if (searchQueryCurso != null && minValue != null && maxValue != null) {
            tutorias = tutoriaService.findByNombreCursoAndCostoBetween(searchQueryCurso, minValue, maxValue);
        } else if (searchQueryDocente != null) {
            tutorias = tutoriaService.findByNombreDocente(searchQueryDocente);
        } else if (searchQueryCurso != null) {
            tutorias = tutoriaService.findByNombreCurso(searchQueryCurso);
        } else if (minValue != null && maxValue != null) {
            tutorias = tutoriaService.findByCostoBetween(minValue, maxValue);
        } else {
            tutorias = tutoriaService.findAll();
        }
        return ResponseEntity.ok(tutorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Tutoria> result = tutoriaService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTutoria(@Valid @RequestBody Tutoria tutoria, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tutoriaService.createTutoria(tutoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTutoria(@PathVariable("id") Long id, @RequestBody Tutoria tutoria) {
        tutoria.setId(id);
        Tutoria tutoriaUpdated = tutoriaService.updateTutoria(tutoria);
        if (tutoriaUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tutoriaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTutoria(@PathVariable("id") Long id) {
        Tutoria tutoriaDeleted = tutoriaService.deleteTutoria(id);
        if (tutoriaDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tutoriaDeleted);
    }
}
