package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;

@Schema(description = "Información del certificado emitido a un usuario en un curso")

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

    @Schema(
            description = "Identificador del curso al que corresponde el certificado",
            example = "45",
            required = true
    )
    private int id_curso;

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


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_curso() {
        return id_curso;
    }
    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public LocalTime getFecha_emision() {
        return fecha_emision;
    }
    public void setFecha_emision(LocalTime fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
}
