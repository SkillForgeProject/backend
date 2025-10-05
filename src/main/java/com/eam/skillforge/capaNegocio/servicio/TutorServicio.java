package com.eam.skillforge.capaNegocio.servicio;

import java.util.List;
import java.util.Optional;

import com.eam.skillforge.capaNegocio.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface TutorServicio {

    List<CursoDto> getCursosPorTutorId(Long tutorId);

    Optional<UsuarioDto> getTutorPorId(Long tutorId);

    void eliminarCurso(Long tutorId);

    CursoDto crearCurso(CursoDto curso);

    CursoDto actualizarCurso(Long cursoId, CursoDto curso);

    ModuloDto crearModulo(ModuloDto modulo);

    ModuloDto actualizarModulo(Long moduloId, ModuloDto modulo);

    boolean eliminarModuloPorId(Long id);

    InscripcionDto asignarCurso(Long usuarioId, Long cursoId);

    void crearEvaluacion(CreacionEvaluacionDto creacionEvaluacion);

    ModuloRecursoDto cargarRecurso(Long moduloId, Long recursoId, MultipartFile archivo, String url);
}
