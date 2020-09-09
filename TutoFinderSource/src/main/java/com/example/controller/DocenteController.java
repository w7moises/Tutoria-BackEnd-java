package com.example.controller;

import com.example.entity.Docente;
import com.example.service.DocenteService;
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
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Docente> docentes = docenteService.findAll();

        return ResponseEntity.ok(docentes);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Docente> result = docenteService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTutor(@Valid @RequestBody Docente docente, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(docenteService.createDocente(docente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTutor(@PathVariable("id") Long id, @RequestBody Docente docente) {
        docente.setId(id);
        Docente docenteRegistered = docenteService.updateDocente(docente);
        if (docenteRegistered == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(docenteRegistered);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTutor(@PathVariable("id") Long id) {
        Docente docenteDeleted = docenteService.deleteDocente(id);
        if (docenteDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(docenteDeleted);
    }

}
