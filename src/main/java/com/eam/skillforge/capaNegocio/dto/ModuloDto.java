package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Información de un módulo dentro de un curso")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuloDto {

    @Schema(
            description = "Identificador único del módulo",
            example = "101",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Identificador del curso al que pertenece el módulo",
            example = "20",
            required = true
    )
    private Long cursoId;

    @Schema(
            description = "Título o nombre del módulo",
            example = "Introducción a la Programación",
            required = true,
            maxLength = 100
    )
    private String titulo;

    @Schema(
            description = "Recurso asociado al módulo (Video, Texto y Practica)",
            example = "Video",
            required = true
    )
    private Long recursoId;

    @Schema(
            description = "Orden del módulo dentro del curso",
            example = "1",
            required = true
    )
    private Long orden;


}


