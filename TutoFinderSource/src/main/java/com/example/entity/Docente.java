package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "docentes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docente_id")
    private Long id;

    @NotEmpty(message = "El nombre del tutor no puede estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "El apellido del tutor no puede estar vacio")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "El DNI del tutor no puede estar vacio")
    @Column(unique = true,nullable = false)
    private String dni;

    @Column(nullable = false)
    private String domicilio;

    @Email(message = "El correo no puede ser vacio")
    @Column(unique = true,nullable = false)
    private String correo;

    @Column(nullable = false)
    private Float costo;

    private Boolean disponibilidad;

    @Column(unique = true,nullable = false,name = "numero_cuenta")
    private String numeroCuenta;

    private Boolean membresia;

    private String status;
}
