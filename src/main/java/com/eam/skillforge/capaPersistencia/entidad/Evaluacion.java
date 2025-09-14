package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "badge")
@Data
@NoArgsConstructor

public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Modulo modulo;

    private String titulo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private TipoEvaluacion tipoEvaluacion;

    private Integer puntajeMax;

}
