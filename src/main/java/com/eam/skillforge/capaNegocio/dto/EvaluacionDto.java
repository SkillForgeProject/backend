package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.TipoEvaluacion;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Información de una evaluación dentro de un módulo"
)

public class EvaluacionDto {

    @Schema(
            description = "Identificador único de la evaluación",
            example = "301",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int id;

    @Schema(
            description = "Identificador del módulo al que pertenece la evaluación",
            example = "101",
            required = true
    )
    private int id_modulo;

    @Schema(
            description = "Título de la evaluación",
            example = "Evaluación Final de Java",
            required = true,
            maxLength = 100
    )
    private String titulo;

    @Schema(
            description = "Tipo de evaluación (1=MCQ, 2=ABIERTA, 3=CERRADA)",
            example = "1",
            required = true
    )
    private TipoEvaluacion id_tipo_evaluacion;

    @Schema(
            description = "Puntaje máximo de la evaluación",
            example = "100",
            required = true
    )
    private int puntajeMax;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_modulo() {
        return id_modulo;
    }
    public void setId_modulo(int id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoEvaluacion getId_tipo_evaluacion() {
        return id_tipo_evaluacion;
    }
    public void setId_tipo_evaluacion(TipoEvaluacion id_tipo_evaluacion) {
        this.id_tipo_evaluacion = id_tipo_evaluacion;
    }

    public int getPuntajeMax() {
        return puntajeMax;
    }
    public void setPuntajeMax(int puntajeMax) {
        this.puntajeMax = puntajeMax;
    }
}