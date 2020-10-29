package com.example.repository;

import com.example.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito,Long> {
    List<Favorito> findByPadreId(Long padreId);
    Optional<Favorito> findByPadreIdAndAndDocenteId(Long padreId, Long docenteId);
}
