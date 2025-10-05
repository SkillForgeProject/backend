package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.TipoEvaluacion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Información de una evaluación dentro de un módulo"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Long id_modulo;

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
    private Long id_tipo_evaluacion;

    @Schema(
            description = "Puntaje máximo de la evaluación",
            example = "100",
            required = true
    )
    private Integer puntajeMax;

}