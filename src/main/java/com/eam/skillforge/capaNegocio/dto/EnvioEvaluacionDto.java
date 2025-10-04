package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        description = "Elemento transaccional para recibir " +
                "la respuesta desde el frontend y mapearla a la entidad Respuesta"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioEvaluacionDto {
    @Schema(
            description = "ID del usuario que envía las respuestas",
            example = "4",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long usuarioId;

    @Schema(
            description = "ID de la evaluación que respondió el usuario",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long evaluacionId;

    @Schema(
            description = "Lista de opciones escogidas"
    )
    private List<Long> respuestasId;

    @Schema(
            description = "Lista de idPreguntas"
    )
    private List<Long> preguntasId;
}