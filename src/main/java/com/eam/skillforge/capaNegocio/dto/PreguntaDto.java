package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Información de una pregunta dentro de una evaluación")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaDto {

    @Schema(
            description = "Identificador único de la pregunta",
            example = "10",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Texto de la pregunta",
            example = "¿Qué es la programación orientada a objetos?",
            required = true,
            maxLength = 255
    )
    private String pregunta;

    @Schema(
            description = "Identificador de la evaluación a la que pertenece la pregunta",
            example = "3",
            required = true
    )
    private Long idEvaluacion;

}