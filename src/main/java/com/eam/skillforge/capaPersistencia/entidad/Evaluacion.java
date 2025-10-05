package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moduloId")
    private Modulo modulo;

    private String titulo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_evaluacion")
    private TipoEvaluacion tipoEvaluacion;

    private Integer puntajeMax;

}
