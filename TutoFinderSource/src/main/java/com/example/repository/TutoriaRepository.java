package com.example.repository;

import com.example.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutoriaRepository extends JpaRepository<Tutoria, Long> {
    List<Tutoria> findByCurso(Curso curso);
    List<Tutoria> findByDocente(Docente docente);
    List<Tutoria> findByCursoAndDocente(Curso curso, Docente docente);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN cursos c ON c.curso_id = t.curso_id "
            + "WHERE "
            + "LOWER(c.nombre) LIKE LOWER(CONCAT('%',:searchQuery, '%'))", nativeQuery = true)
    List<Tutoria> findByNombreCurso(@Param("searchQuery") String searchQuery);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN docentes d ON d.docente_id = t.docente_id "
            + "WHERE "
            + "LOWER(CONCAT(d.nombre, ' ', d.apellido)) LIKE LOWER(CONCAT('%',:searchQuery, '%'))", nativeQuery = true)
    List<Tutoria> findByNombreDocente(@Param("searchQuery") String searchQuery);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN docentes d ON d.docente_id = t.docente_id "
            + "WHERE "
            + "d.costo >= :minValue AND d.costo <= :maxValue", nativeQuery = true)
    List<Tutoria> findByCostoBetween(@Param("minValue") float minValue, @Param("maxValue") float maxValue);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN docentes d ON d.docente_id = t.docente_id "
            + "LEFT JOIN cursos c ON c.curso_id = t.curso_id "
            + "WHERE "
            + "LOWER(c.nombre) LIKE LOWER(CONCAT('%',:searchCursoQuery, '%')) AND "
            + "LOWER(CONCAT(d.nombre, ' ', d.apellido)) LIKE LOWER(CONCAT('%',:searchDocenteQuery, '%')) ", nativeQuery = true)
    List<Tutoria> findByNombreCursoAndNombreDocente(@Param("searchCursoQuery") String searchCursoQuery, @Param("searchDocenteQuery") String searchDocenteQuery);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN docentes d ON d.docente_id = t.docente_id "
            + "LEFT JOIN cursos c ON c.curso_id = t.curso_id "
            + "WHERE "
            + "LOWER(c.nombre) LIKE LOWER(CONCAT('%',:searchQuery, '%')) AND "
            + "d.costo >= :minValue AND d.costo <= :maxValue", nativeQuery = true)
    List<Tutoria> findByNombreCursoAndCostoBetween(@Param("searchQuery") String searchQuery, @Param("minValue") float minValue, @Param("maxValue") float maxValue);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN docentes d ON d.docente_id = t.docente_id "
            + "LEFT JOIN cursos c ON c.curso_id = t.curso_id "
            + "WHERE "
            + "LOWER(CONCAT(d.nombre, ' ', d.apellido)) LIKE LOWER(CONCAT('%',:searchQuery, '%')) AND "
            + "d.costo >= :minValue AND d.costo <= :maxValue", nativeQuery = true)
    List<Tutoria> findByNombreDocenteAndCostoBetween(@Param("searchQuery") String searchQuery, @Param("minValue") float minValue, @Param("maxValue") float maxValue);

    @Query(value = ""
            + "SELECT * FROM tutorias t "
            + "LEFT JOIN docentes d ON d.docente_id = t.docente_id "
            + "LEFT JOIN cursos c ON c.curso_id = t.curso_id "
            + "WHERE "
            + "LOWER(c.nombre) LIKE LOWER(CONCAT('%',:searchCursoQuery, '%')) AND "
            + "LOWER(CONCAT(d.nombre, ' ', d.apellido)) LIKE LOWER(CONCAT('%',:searchDocenteQuery, '%')) AND "
            + "d.costo >= :minValue AND d.costo <= :maxValue", nativeQuery = true)
    List<Tutoria> findByNombreCursoAndNombreDocenteAndCostoBetween(@Param("searchCursoQuery") String searchCursoQuery, @Param("searchDocenteQuery") String searchDocenteQuery, @Param("minValue") float minValue, @Param("maxValue") float maxValue);
}
