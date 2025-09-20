package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "badge")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Curso curso;

    private String titulo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recurso")
    private List<Recurso> recursos;

    private String orden;

}
