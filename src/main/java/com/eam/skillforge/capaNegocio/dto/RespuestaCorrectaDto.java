package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "DTO para recibir la información tabulada de las respuestas correctas " +
                "en función de cada ID de pregunta y ID de evaluación"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaCorrectaDto {

    @Schema(
            description = "Identificador de la pregunta",
            example = "5",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long preguntaId;

    @Schema(
            description = "Identificador de la opción correcta",
            example = "2",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long opcionCorrectaId;
}
