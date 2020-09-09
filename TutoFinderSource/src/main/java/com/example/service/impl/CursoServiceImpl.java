package com.example.service.impl;

import com.example.entity.Curso;
import com.example.repository.CursoRepository;
import com.example.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso createCurso(Curso curso) {
        if (curso.getId() != null) {
            Optional<Curso> cursoRegistered = this.findById(curso.getId());
            if (cursoRegistered.isPresent()) {
                return cursoRegistered.get();
            }
        }
        curso.setStatus("CREATED");
        return cursoRepository.save(curso);
    }

    @Override
    public Curso updateCurso(Curso curso) {
        if (curso.getId() == null) return null;
        Optional<Curso> cursoRegistered = this.findById(curso.getId());
        if (!cursoRegistered.isPresent()) return null;
        Curso cursoUpdated = cursoRegistered.get();
        cursoUpdated.setNombre(curso.getNombre());
        cursoUpdated.setStatus("UPDATED");
        return cursoRepository.save(cursoUpdated);
    }

    @Override
    public Curso deleteCurso(Long id) {
        if (id == null) return null;

        Optional<Curso> cursoRegistered = this.findById(id);
        if (!cursoRegistered.isPresent()) return null;

        Curso cursoDeleted = cursoRegistered.get();
        cursoDeleted.setStatus("DELETED");

        return cursoRepository.save(cursoDeleted);
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }
}
