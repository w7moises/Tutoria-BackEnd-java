package com.example.repository;

//import com.example.entity.Anuncio;
import com.example.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente,Long>{
//    Tutor findByDniTutor(String dniTutor);
//    List<Tutor> findByApellidoTutor(String apellidoTutor);
//    List<Tutor> findByAnuncios(Anuncio anuncio);
}
