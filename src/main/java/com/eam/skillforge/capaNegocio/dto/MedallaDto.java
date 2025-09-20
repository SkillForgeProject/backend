package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Información de una medalla otorgada a un usuario"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedallaDto {

    @Schema(
            description = "Identificador único de la medalla",
            example = "10",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(
            description = "Nombre de la medalla",
            example = "Medalla de Excelencia",
            required = true)
    private String nombre;

    @Schema(
            description = "Criterio para obtener la medalla",
            example = "Completar todos los módulos con más del 90% de calificación",
            required = true)
    private String criterio;

    @Schema(
            description = "Icono o URL de la medalla",
            example = "https://miapp.com/icons/excelencia.png",
            required = true)
    private String icono;

    private String nombreUsuario;
    private Long idUsuario;

}
