package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.CursoDiferenciaUsuariosDto;
import com.eam.skillforge.capaNegocio.dto.TopCursoDto;

import java.util.List;

public interface CursoServicio {
    CursoDto getCursoPorId(Long cursoId);

    List<CursoDto> buscarCursosPorNivel(Long nivelId);

    CursoDto actualizarCurso(Long id, CursoDto curso);

    List<TopCursoDto> getTopCursosMejoresPuntuados();

    List<CursoDiferenciaUsuariosDto> getCursosMasTomados();

    List<CursoDto> getCursosActivos();
}
