package com.eam.skillforge.capaNegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuariosPorCursoMesDTO {
    private String cursoTitulo;
    private String mes;
    private Long cantidadUsuarios;
}