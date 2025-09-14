package com.eam.skillforge.capaPersistencia.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "badge")
@Data
@NoArgsConstructor

public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    private Float progreso;

    private LocalDateTime fechaInscripcion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    private Estado estado;

}
