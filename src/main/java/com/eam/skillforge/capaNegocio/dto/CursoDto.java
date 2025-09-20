package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

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
    private Long id_categoria;

    @Schema(
            description = "Nombre de la cageroría",
            example = "CAPACITACION",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String nombreCategoria;

}
