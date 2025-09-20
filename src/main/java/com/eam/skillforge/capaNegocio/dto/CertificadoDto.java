package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Schema(description = "Información del certificado emitido a un usuario en un curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificadoDto {

    @Schema(
            description = "Identificador único del certificado",
            example = "5001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int id;

    @Schema(
            description = "Identificador del usuario al que pertenece el certificado",
            example = "102",
            required = true
    )
    private int id_usuario;

    private String nombreUsuario;

    @Schema(
            description = "Identificador del curso al que corresponde el certificado",
            example = "45",
            required = true
    )
    private int id_curso;

    private String nombreCurso;

    @Schema(
            description = "Fecha y hora de emisión del certificado",
            example = "14:30:00",
            required = true
    )
    private LocalTime fecha_emision;

    @Schema(
            description = "Hash único para validar el certificado",
            example = "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3",
            required = true
    )
    private String hash;

}
