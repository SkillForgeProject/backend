package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;

import java.util.List;

public interface ModuloServicio {
    ModuloDto guardarModulo(ModuloDto modulo);

    boolean eliminarModuloPorId(Long id);

    ModuloDto getModuloPorId(Long id);

    List<ModuloDto> getModulosPorCursoId(Long cursoId);
}
