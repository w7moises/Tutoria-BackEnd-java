package com.example.service;

import com.example.entity.Alumno;
import com.example.entity.Padre;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {
    Alumno createAlumno(Alumno alumno);
    Alumno updateAlumno(Alumno alumno);
    Alumno deleteAlumno(Long id);

    Optional<Alumno> findById(Long id);
    List<Alumno> findAll();
    List<Alumno> findByApellido(String apellido);
    List<Alumno> findByPadre(Padre padre);
    List<Alumno> findByPadreAndApellido(Padre padre, String apellido);
}
