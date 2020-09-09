package com.example.controller;

import com.example.entity.Tarjeta;
import com.example.service.TarjetaService;
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
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Tarjeta> tarjetas = tarjetaService.findByAll();
        if (tarjetas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tarjetas);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Tarjeta> result = tarjetaService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTarjeta(@Valid @RequestBody Tarjeta tarjeta, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.createTarjeta(tarjeta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTarjeta(@PathVariable("id")Long id, @RequestBody Tarjeta tarjeta) {
        tarjeta.setId(id);
        Tarjeta tarjetaUpdated = tarjetaService.updateTarjeta(tarjeta);
        if (tarjetaUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarjetaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarjeta(@PathVariable("id") Long id) {
        Tarjeta tarjetaDeleted = tarjetaService.deleteTarjeta(id);
        if (tarjetaDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarjetaDeleted);
    }

}
