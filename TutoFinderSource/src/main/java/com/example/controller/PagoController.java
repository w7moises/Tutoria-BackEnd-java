package com.example.controller;

import com.example.entity.Padre;
import com.example.entity.Pago;
import com.example.entity.Tarjeta;
import com.example.service.PagoService;
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
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    private ResponseEntity<?> getAll(@RequestParam(value = "padreId", required = false) Long padreId, @RequestParam(value = "tarjetaId", required = false) Long tarjetaId) {
        List<Pago> pagos = new ArrayList<>();
        if (padreId != null && tarjetaId != null) {
            pagos = pagoService.findByPadreAndTarjeta(Padre.builder().id(padreId).build(), Tarjeta.builder().id(tarjetaId).build());
        } else if (padreId != null) {
            pagos = pagoService.findByPadre(Padre.builder().id(padreId).build());
        } else if (tarjetaId != null) {
            pagos = pagoService.findByTarjeta(Tarjeta.builder().id(tarjetaId).build());
        } else {
            pagos = pagoService.findAll();
            if (pagos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Pago> result = pagoService.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return  ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPago(@Valid @RequestBody Pago pago, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.createPago(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePago(@PathVariable("id") Long id, @RequestBody Pago pago) {
        pago.setId(id);
        Pago pagoUpdated = pagoService.updatePago(pago);
        if (pagoUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePago(@PathVariable("id") Long id) {
        Pago pagoDeleted = pagoService.deletePago(id);
        if (pagoDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(pagoDeleted);
    }
}
