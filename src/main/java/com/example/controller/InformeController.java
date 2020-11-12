package com.example.controller;

import com.example.entity.Informe;
import com.example.entity.Membresia;
import com.example.service.InformeService;
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
@RequestMapping("/informes")
public class InformeController {

    @Autowired
    private InformeService informeService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Informe> informes = informeService.findAll();

        if (informes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(informes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<?> result = informeService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInforme(@PathVariable("id") Long id, @RequestBody Informe informe) {
        informe.setId(id);
        Informe informeUpdated = informeService.updateInforme(informe);
        if (informeUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(informe);
    }

    @PostMapping
    public ResponseEntity<?> createInforme(@Valid @RequestBody Informe informe, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(informeService.createInforme(informe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInforme(@PathVariable("id") Long id) {
        Informe informeDeleted = informeService.deleteInforme(id);
        if (informeDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(informeDeleted);
    }
}
