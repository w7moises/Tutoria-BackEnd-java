package com.example.service.impl;

import com.example.entity.Favorito;
import com.example.repository.FavoritoRepository;
import com.example.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Override
    public Favorito createFavorito(Favorito favorito) {
        Optional<Favorito> favoritoRegistered = Optional.empty();
        if (favorito.getId() != null) {
            favoritoRegistered = this.findById(favorito.getId());
            if (favoritoRegistered.isPresent()) {
                return favoritoRegistered.get();
            }
        } else if (favorito.getPadre() != null && favorito.getPadre().getId() != null && favorito.getDocente() != null && favorito.getDocente().getId() != null) {
            favoritoRegistered = this.findByPadreIdAndAndDocenteId(favorito.getPadre().getId(), favorito.getDocente().getId());
            if (favoritoRegistered.isPresent()) {
                Favorito favoritoUpdated = favoritoRegistered.get();
                favoritoUpdated.setStatus("UPDATED");
                return favoritoRepository.save(favoritoUpdated);
            }
        }
        favorito.setStatus("CREATED");
        return favoritoRepository.save(favorito);
    }

    @Override
    public Favorito updateFavorito(Favorito favorito) {
        if (favorito.getId() == null) return null;
        Optional<Favorito> favoritoRegistered = this.findById(favorito.getId());
        if (!favoritoRegistered.isPresent()) return null;
        Favorito favoritoUpdated = favoritoRegistered.get();
        favoritoUpdated.setDocente(favorito.getDocente());
        favoritoUpdated.setPadre(favorito.getPadre());
        favoritoUpdated.setStatus("UPDATED");
        return favoritoRepository.save(favoritoUpdated);
    }

    @Override
    public Favorito deleteFavorito(Long id) {
        if (id == null) return null;
        Optional<Favorito> favoritoRegistered = this.findById(id);
        if (!favoritoRegistered.isPresent()) return null;
        Favorito favoritoDeleted = favoritoRegistered.get();
        favoritoDeleted.setStatus("DELETED");
        return favoritoRepository.save(favoritoDeleted);
    }

    @Override
    public Optional<Favorito> findById(Long id) {
        return favoritoRepository.findById(id);
    }

    @Override
    public List<Favorito> findAll() {
        return favoritoRepository.findAll();
    }

    @Override
    public List<Favorito> findByPadreId(Long padreId) {
        return favoritoRepository.findByPadreId(padreId);
    }

    @Override
    public Optional<Favorito> findByPadreIdAndAndDocenteId(Long padreId, Long docenteId) {
        return favoritoRepository.findByPadreIdAndAndDocenteId(padreId, docenteId);
    }
}
