package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Estado;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(
        description = "Información de la inscripción de un usuario en un curso"
)

public class InscripcionDto {

    @Schema(
            description = "Identificador único de la inscripción",
            example = "8001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int id;

    @Schema(
            description = "Identificador del curso inscrito",
            example = "2001",
            required = true
    )
    private int id_curso;

    @Schema(
            description = "Progreso del usuario en el curso expresado como porcentaje",
            example = "75.5",
            required = true
    )
    private float progreso;

    @Schema(
            description = "Fecha en que se realizó la inscripción",
            example = "2025-09-13",
            required = true
    )
    private LocalDate fechaInscripcion;

    @Schema(
            description = "Estado de la inscripción (noInscrito=1, inscrito=2, enProgreso=3, completado=4)",
            example = "2",
            required = true
    )
    private Estado id_estado;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_curso() {
        return id_curso;
    }
    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public float getProgreso() {
        return progreso;
    }
    public void setProgreso(float progreso) {
        this.progreso = progreso;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Estado getId_estado() {
        return id_estado;
    }
    public void setId_estado(Estado id_estado) {
        this.id_estado = id_estado;
    }
}