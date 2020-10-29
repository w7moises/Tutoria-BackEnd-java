package com.example.service;

import com.example.entity.Favorito;

import java.util.List;
import java.util.Optional;

public interface FavoritoService {
    Favorito createFavorito(Favorito favorito);
    Favorito updateFavorito(Favorito favorito);
    Favorito deleteFavorito(Long id);

    Optional<Favorito> findById(Long id);
    List<Favorito> findAll();

    List<Favorito> findByPadreId(Long padreId);
    Optional<Favorito> findByPadreIdAndAndDocenteId(Long padreId, Long docenteId);
}
