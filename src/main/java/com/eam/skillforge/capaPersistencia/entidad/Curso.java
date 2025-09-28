package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private Long duracionEstimada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nivel", nullable = false)
    private Categoria categoria;
}
