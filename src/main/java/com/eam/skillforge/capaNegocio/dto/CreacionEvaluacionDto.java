package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
public class CreacionEvaluacionDto {

    @Schema(description = "Información de la evaluación")
    @Valid
    @NotNull(message = "La evaluación es requerida")
    private EvaluacionDto evaluacion;

    @Schema(description = "Lista de preguntas de la evaluación")
    @Valid
    @NotNull(message = "Las preguntas son requeridas")
    @Size(min = 1, message = "Debe haber al menos una pregunta")
    private List<PreguntaDto> preguntas;

    @Schema(description = "Lista de opciones para todas las preguntas")
    @Valid
    @NotNull(message = "Las opciones son requeridas")
    @Size(min = 1, message = "Debe haber al menos una opción")
    private List<OpcionPreguntaDto> opciones;
}
