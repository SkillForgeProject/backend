package com.eam.skillforge.capaNegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuloRecursoDto {

    private Long id;

    private Long moduloId;

    private Long recursoId;

    private String rutaArchivo;

    private String url;
}
