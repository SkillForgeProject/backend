package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un curso")

public class CursoDto {

    @Schema(
            description = "Identificador único del curso",
            example = "2001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int id;

    @Schema(
            description = "Título del curso",
            example = "Programación en Java",
            required = true,
            maxLength = 100
    )
    private String titulo;

    @Schema(
            description = "Descripción del curso",
            example = "Curso básico de programación en Java con ejemplos prácticos",
            required = true
    )
    private String descripcion;

    @Schema(
            description = "Duración estimada del curso en minutos",
            example = "120",
            required = true
    )
    private int duracionEstim;

    @Schema(
            description = "Categoría a la que pertenece el curso  CAPACITACION, INDUCCION o ESPECIALIZACION",
            example = "CAPACITACION",
            required = true
    )
    private Categoria id_categoria;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionEstim() {
        return duracionEstim;
    }
    public void setDuracionEstim(int duracionEstim) {
        this.duracionEstim = duracionEstim;
    }

    public Categoria getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(Categoria id_categoria) {
        this.id_categoria = id_categoria;
    }
}
