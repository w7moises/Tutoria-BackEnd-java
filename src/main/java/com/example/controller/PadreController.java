package com.example.controller;

import com.example.entity.Padre;
import com.example.service.PadreService;
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
@RequestMapping("/padres")
public class PadreController {

    @Autowired
    private PadreService padreService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Padre> padres=padreService.findAll();

        if (padres.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(padres);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Padre> result = padreService.findById(id);
        if (!result.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getByDni(@PathVariable("dni") String dni) {
        Padre result = padreService.findByDni(dni);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPadre(@Valid @RequestBody Padre padre, BindingResult result) {
        if (result.hasErrors()) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(padreService.createPadre(padre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePadre(@PathVariable("id") Long id, @RequestBody Padre padre) {
        padre.setId(id);
        Padre padreRegistered = padreService.updatePadre(padre);
        if (padreRegistered == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(padreRegistered);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePadre(@PathVariable("id") Long id) {
        Padre padreDeleted = padreService.deletePadre(id);
        if (padreDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(padreDeleted);
    }
}
