package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "alumnos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alumno{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alummno_id")
    private Long id;

    @NotEmpty(message = "El nombre del alumno no debe estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "El apellido del alumno no debe estar vacio")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "El grado de estudio del alumno no debe estar vacio")
    @Column(name = "grado_estudio",nullable = false)
    private String gradoEstudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Padre padre;

    @ManyToMany(mappedBy = "alumnos", fetch = FetchType.LAZY)
    private Set<Tutoria> tutorias = new HashSet<>();

    private String dni;

    private String correo;

    private String status;
}
