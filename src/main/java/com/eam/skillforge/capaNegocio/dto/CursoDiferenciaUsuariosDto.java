package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información de cursos con diferencia de usuarios aprendices vs inscritos")
public class CursoDiferenciaUsuariosDto {

    @Schema(
            description = "Diferencia entre total de aprendices y usuarios inscritos en el curso",
            example = "15",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer diferenciaUsuarios;

    @Schema(
            description = "Identificador único del curso",
            example = "2001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long cursoId;

    @Schema(
            description = "Título del curso",
            example = "Programación en Java",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String titulo;
}