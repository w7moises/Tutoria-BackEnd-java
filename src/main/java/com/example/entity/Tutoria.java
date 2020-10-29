package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tutorias")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tutoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutoria_id")
    private Long id;

    @Positive(message = "La cantidad de minutos debe ser mayor a cero")
    @Column(name = "cantidad_minutos", nullable = false)
    private int cantidadMinutos;

    @Column(name = "descripcion_tutoria")
    private String descripcionTutoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Curso curso;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tutoria_alumno",
            joinColumns = {@JoinColumn(name = "tutoria_id")},
            inverseJoinColumns = { @JoinColumn(name = "alumno_id") })
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Set<Alumno> alumnos = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pago_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Pago pago;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Docente docente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "informe_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Informe informe;

    private String status;
}
