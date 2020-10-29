package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "padres")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Padre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "padre_id")
    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    private String correo;

    private String status;
}