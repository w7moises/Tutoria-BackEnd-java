package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "informes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Informe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "informe_id")
    private Long id;

    @Column(name = "descripcion_informe")
    private String descripcionInforme;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "informe_tutoria",
            joinColumns = {@JoinColumn(name = "informe_id")},
            inverseJoinColumns = { @JoinColumn(name = "tutoria_id") })
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Set<Tutoria> tutorias = new HashSet<>();

    private String status;
}
