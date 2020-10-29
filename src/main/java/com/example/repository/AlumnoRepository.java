package com.example.repository;

import com.example.entity.Alumno;
import com.example.entity.Padre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno,Long> {
    List<Alumno> findByApellido(String apellido);
    List<Alumno> findByPadre(Padre padre);
    List<Alumno> findByPadreAndApellido(Padre padre, String apellido);
}
