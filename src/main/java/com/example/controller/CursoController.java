package com.example.controller;


import com.example.entity.Curso;
import com.example.service.CursoService;
import com.example.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Curso> cursos = cursoService.findAll();

        if (cursos.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cursos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Curso> result = cursoService.findById(id);
        if (!result.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createCurso(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.createCurso(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable("id") Long id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso cursoRegistered = cursoService.updateCurso(curso);
        if (cursoRegistered == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoRegistered);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable("id") Long id) {
        Curso cursoDeleted = cursoService.deleteCurso(id);
        if (cursoDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoDeleted);
    }
}
