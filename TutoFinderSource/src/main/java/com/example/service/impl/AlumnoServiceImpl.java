package com.example.service.impl;

import com.example.entity.Alumno;
import com.example.entity.Padre;
import com.example.repository.AlumnoRepository;
import com.example.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno createAlumno(Alumno alumno) {
        if (alumno.getId() != null) {
            Optional<Alumno> alumnoRegistered = this.findById(alumno.getId());
            if (alumnoRegistered.isPresent()) {
                return alumnoRegistered.get();
            }
        }
        alumno.setStatus("CREATED");
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno updateAlumno(Alumno alumno) {
        if (alumno.getId() == null) return null;

        Optional<Alumno> alumnoRegistered = this.findById(alumno.getId());
        if (!alumnoRegistered.isPresent()) return null;
        Alumno alumnoUpdated = alumnoRegistered.get();
        alumnoUpdated.setNombre(alumno.getNombre());
        alumnoUpdated.setStatus("UPDATED");
        return alumnoRepository.save(alumnoUpdated);
    }

    @Override
    public Alumno deleteAlumno(Long id) {
        if (id == null) return null;

        Optional<Alumno> alumnoRegistered = this.findById(id);
        if (!alumnoRegistered.isPresent()) return null;

        Alumno alumnoDeleted = alumnoRegistered.get();
        alumnoDeleted.setStatus("DELETED");

        return alumnoRepository.save(alumnoDeleted);
    }

    @Override
    public List<Alumno> findByApellido(String apellido) { return alumnoRepository.findByApellido(apellido); }

    @Override
    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public List<Alumno> findByPadre(Padre padre) {
        return alumnoRepository.findByPadre(padre);
    }

    @Override
    public List<Alumno> findByPadreAndApellido(Padre padre, String apellido) {
        return alumnoRepository.findByPadreAndApellido(padre, apellido);
    }
}
