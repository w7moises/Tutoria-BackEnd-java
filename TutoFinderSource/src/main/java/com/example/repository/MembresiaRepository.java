package com.example.repository;

import com.example.entity.Docente;
import com.example.entity.Membresia;
import com.example.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembresiaRepository extends JpaRepository<Membresia,Long> {

}
