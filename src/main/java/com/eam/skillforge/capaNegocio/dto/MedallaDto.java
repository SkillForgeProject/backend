package com.eam.skillforge.capaNegocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Información de una medalla otorgada a un usuario"
)

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


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCriterio() {
        return criterio;
    }
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getIcono() {
        return icono;
    }
    public void setIcono(String icono) {
        this.icono = icono;
    }
}
