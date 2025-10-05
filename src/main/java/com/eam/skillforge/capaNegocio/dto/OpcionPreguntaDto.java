package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Información de una opción asociada a una pregunta de evaluación")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpcionPreguntaDto {

    @Schema(
            description = "Identificador único de la opción de pregunta",
            example = "15",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Identificador de la pregunta a la que pertenece la opción",
            example = "10",
            required = true
    )
    private Long idPregunta;

    @Schema(
            description = "Texto de la opción de respuesta",
            example = "La programación orientada a objetos se basa en el uso de clases y objetos",
            required = true,
            maxLength = 255
    )
    private String opcion;

    @Schema(
            description = "Indica si la opción es la respuesta correcta",
            example = "true",
            required = true
    )
    private Boolean esCorrecto;
}