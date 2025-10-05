package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información de curso con su puntuación promedio")
public class TopCursoDto {

    @Schema(
            description = "Identificador único del curso",
            example = "2001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Título del curso",
            example = "Programación en Java",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String titulo;

    @Schema(
            description = "Puntuación promedio del curso",
            example = "4.5",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Double puntuacion;
}