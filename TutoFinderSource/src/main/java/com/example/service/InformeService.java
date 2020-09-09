package com.example.service;

import com.example.entity.Informe;

import java.util.List;
import java.util.Optional;

public interface InformeService {
    Informe createInforme(Informe informe);
    Informe updateInforme(Informe informe);
    Informe deleteInforme(Long id);

    Optional<Informe> findById(Long id);
    List<Informe> findAll();
}
