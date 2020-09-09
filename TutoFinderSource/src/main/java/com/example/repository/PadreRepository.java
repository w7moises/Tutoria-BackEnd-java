package com.example.repository;

import com.example.entity.Padre;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

public interface PadreRepository extends JpaRepository<Padre,Long> {
//    @Query(value = "SELECT p FROM padres t WHERE p.dni := dni", nativeQuery=true)
//    Padre findByDni(@Param("dni") String dni);
    Padre findByDni(String dni);
}
