package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "opcion_pregunta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpcionPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    private String opcion;

    private Boolean esCorrecto;
}









