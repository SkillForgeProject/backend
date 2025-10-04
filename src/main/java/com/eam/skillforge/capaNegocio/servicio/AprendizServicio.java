package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;


public interface AprendizServicio {

    UsuarioDto getUsuarioPorId(Long id);
    void putProgresoModulo(int usuarioId,Double progreso, int moduloId);
}
