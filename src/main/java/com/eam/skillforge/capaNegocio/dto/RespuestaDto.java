package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Schema(
        description = "Información de una respuesta enviada por un usuario en una evaluación"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaDto {

    @Schema(
            description = "Identificador único de la respuesta",
            example = "5001",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Identificador de la evaluación a la que pertenece la respuesta",
            example = "301",
            required = true)
    private Long id_evaluacion;

    @Schema(description = "Identificador del usuario que envió la respuesta",
            example = "101",
            required = true)
    private Long id_usuario;

    @Schema(description = "Puntuación obtenida en la respuesta",
            example = "95.5",
            required = true)
    private Float puntuacion;

    @Schema(description = "Fecha y hora en que se registró la respuesta",
            example = "2025-09-14T14:30:00",
            required = true)
    private LocalDateTime fecha;

    // no borrar, lo necesito para instanciar una parcial
    public RespuestaDto(Long id_evaluacion, Long id_usuario) {
        this.id_evaluacion = id_evaluacion;
        this.id_usuario = id_usuario;
    }
}
