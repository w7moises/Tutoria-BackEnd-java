package com.example.controller;

import com.example.entity.Alumno;
import com.example.entity.Padre;
import com.example.service.AlumnoService;
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
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "parentId", required = false) Long padreId, @RequestParam(name = "apellido", required = false) String apellido) {
        List<Alumno> alumnos = new ArrayList<>();

        if (padreId != null && apellido != null) {
            alumnos = alumnoService.findByPadreAndApellido(Padre.builder().id(padreId).build(), apellido);
        } else if (padreId != null) {
            alumnos = alumnoService.findByPadre(Padre.builder().id(padreId).build());
        } else if (apellido != null) {
            alumnos = alumnoService.findByApellido(apellido);
        } else {
            alumnos = alumnoService.findAll();
            if (alumnos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Alumno> result = alumnoService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createAlumno(@Valid @RequestBody Alumno alumno, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.createAlumno(alumno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@PathVariable("id") Long id, @RequestBody Alumno alumno) {
        alumno.setId(id);
        Alumno alumnoRegistered = alumnoService.updateAlumno(alumno);
        if (alumnoRegistered == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumnoRegistered);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable("id") Long id) {
        Alumno alumnoDeleted = alumnoService.deleteAlumno(id);
        if (alumnoDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumnoDeleted);
    }
}
