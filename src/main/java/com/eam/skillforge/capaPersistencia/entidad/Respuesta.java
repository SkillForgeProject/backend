package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "badge")
@Data
@NoArgsConstructor

public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Evaluacion evaluacion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Usuario usuario;

    private Float puntuacion;

    private LocalDateTime fecha;


}
