package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información de puntuación de un curso por usuario")
public class CursosPuntuacionDto {

    @Schema(
            description = "Identificador único de la puntuación",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Identificador del curso",
            example = "2001",
            required = true
    )
    private Long cursoId;

    @Schema(
            description = "Título del curso",
            example = "Programación en Java",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String tituloCurso;

    @Schema(
            description = "Identificador del usuario",
            example = "1001",
            required = true
    )
    private Long usuarioId;

    @Schema(
            description = "Nombre del usuario",
            example = "Juan Pérez",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String nombreUsuario;

    @Schema(
            description = "Puntuación otorgada al curso (0.0 - 5.0)",
            example = "4.5",
            required = true,
            minimum = "0.0",
            maximum = "5.0"
    )
    private Float puntuacion;
}