package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(
        description = "Información de una respuesta enviada por un usuario en una evaluación"
)
public class RespuestaDto {

    @Schema(
            description = "Identificador único de la respuesta",
            example = "5001",
            accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Identificador de la evaluación a la que pertenece la respuesta",
            example = "301",
            required = true)
    private int id_evaluacion;

    @Schema(description = "Identificador del usuario que envió la respuesta",
            example = "101",
            required = true)
    private int id_usuario;

    @Schema(description = "Puntuación obtenida en la respuesta",
            example = "95.5",
            required = true)
    private float puntuacion;

    @Schema(description = "Fecha y hora en que se registró la respuesta",
            example = "2025-09-14T14:30:00",
            required = true)
    private LocalDateTime fecha;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_evaluacion() {
        return id_evaluacion;
    }
    public void setId_evaluacion(int id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public float getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
