package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;

import java.util.List;

public interface CursoServicio {
    CursoDto getCursoPorId(Long cursoId);

    List<CursoDto> buscarCursosPorNivel(Long nivelId);
}
