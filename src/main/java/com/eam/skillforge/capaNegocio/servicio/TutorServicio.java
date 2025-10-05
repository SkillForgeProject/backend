package com.eam.skillforge.capaNegocio.servicio;

import java.util.List;
import java.util.Optional;

import com.eam.skillforge.capaNegocio.dto.CreacionEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;

public interface TutorServicio {

    List<CursoDto> getCursosPorTutorId(Long tutorId);

    Optional<UsuarioDto> getTutorPorId(Long tutorId);

    void eliminarCurso(Long tutorId);

    CursoDto crearCurso(CursoDto curso);

    CursoDto actualizarCurso(Long cursoId, CursoDto curso);

    ModuloDto crearModulo(ModuloDto modulo);

    ModuloDto actualizarModulo(Long moduloId, ModuloDto modulo);

    boolean eliminarModuloPorId(Long id);

    void crearEvaluacion(CreacionEvaluacionDto creacionEvaluacion);
}
