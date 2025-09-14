package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "badge")
@Data
@NoArgsConstructor

public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Curso curso;

    private String titulo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recurso")
    private Recurso tipo;

    private String orden;

}
