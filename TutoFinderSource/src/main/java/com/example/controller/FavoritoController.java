package com.example.controller;

import com.example.entity.Docente;
import com.example.entity.Favorito;
import com.example.entity.Padre;
import com.example.service.DocenteService;
import com.example.service.FavoritoService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private PadreService padreService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Favorito> favoritos = favoritoService.findAll();
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoritos);
    }

    @GetMapping("/{padreId}")
    public ResponseEntity<?> getByPadreId(@PathVariable("padreId") Long padreId) {
        if (padreId == null) return ResponseEntity.badRequest().build();
        List<Favorito> favoritos = favoritoService.findByPadreId(padreId);
        return ResponseEntity.ok(favoritos);
    }

    @PostMapping
    public ResponseEntity<?> createFavorito(@Valid @RequestBody Favorito favorito, BindingResult result){
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.createFavorito(favorito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFavorito(@PathVariable("id") Long id, @RequestBody Favorito favorito) {
        favorito.setId(id);
        Favorito favoritoUpdated = favoritoService.updateFavorito(favorito);
        if (favoritoUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favoritoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorito(@PathVariable("id") Long id) {
        Favorito favoritoDeleted = favoritoService.deleteFavorito(id);
        if (favoritoDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favoritoDeleted);
    }
}
