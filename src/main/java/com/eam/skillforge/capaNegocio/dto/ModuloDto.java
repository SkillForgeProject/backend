package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Recurso;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un módulo dentro de un curso")

public class ModuloDto {

    @Schema(
            description = "Identificador único del módulo",
            example = "101",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int id;

    @Schema(
            description = "Identificador del curso al que pertenece el módulo",
            example = "20",
            required = true
    )
    private int id_curso;

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
    private Recurso recurso;

    @Schema(
            description = "Orden del módulo dentro del curso",
            example = "1",
            required = true
    )
    private int orden;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_curso() {
        return id_curso;
    }
    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Recurso getRecurso() {
        return recurso;
    }
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public int getOrden() {
        return orden;
    }
    public void setOrden(int orden) {
        this.orden = orden;
    }
}


