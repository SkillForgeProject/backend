package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(
        description = "Información de la inscripción de un usuario en un curso"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDto {

    @Schema(
            description = "Identificador único de la inscripción",
            example = "8001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    private Long usuarioId;

    @Schema(
            description = "Identificador del curso inscrito",
            example = "2001",
            required = true
    )
    private Long cursoId;

    private Long moduloId;

    @Schema(
            description = "Progreso del usuario en el curso expresado como porcentaje",
            example = "75.5",
            required = true
    )
    private Float progreso;

    @Schema(
            description = "Fecha en que se realizó la inscripción",
            example = "2025-09-13",
            required = true
    )
    private LocalDateTime fechaInscripcion;

    private LocalDateTime fechaUltimoEstado;

    @Schema(
            description = "Estado de la inscripción (noInscrito=1, inscrito=2, enProgreso=3, completado=4)",
            example = "2",
            required = true
    )
    private Long estado;
}