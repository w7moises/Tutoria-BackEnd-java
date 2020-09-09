package com.example.controller;

import com.example.entity.*;
import com.example.service.MembresiaService;
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
@RequestMapping("/membresias")
public class MembresiaController {
    @Autowired
    private MembresiaService membresiaService;

    @GetMapping
    private ResponseEntity<?> getAll() {
        List<Membresia> membresias = membresiaService.findAll();

        if (membresias.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(membresias);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Membresia> result = membresiaService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return  ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMembresia(@Valid @RequestBody Membresia membresia, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(membresiaService.createMembresia(membresia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMembresia(@PathVariable("id") Long id, @RequestBody Membresia membresia) {
        membresia.setId(id);
        Membresia membresiaUpdated = membresiaService.updateMembresia(membresia);
        if (membresiaUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(membresiaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMembresia(@PathVariable("id") Long id) {
        Membresia membresiaDeleted = membresiaService.deleteMembresia(id);
        if (membresiaDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(membresiaDeleted);
    }


}
