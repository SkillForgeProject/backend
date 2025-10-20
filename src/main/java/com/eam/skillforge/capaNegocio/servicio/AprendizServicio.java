package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.Estado;

import java.util.List;


public interface AprendizServicio {

    UsuarioDto getUsuarioPorId(Long id);

    void putProgresoModulo(int usuarioId, Double progreso, int moduloId);

    Double getProgresoCurso(int usuarioId, int cursoId);

    List<CursoDto> getCursosPorIdUsuario(Long idUsuario);

    Integer getCantidadCertificaciones(Long usuarioId);

    List<CursoDto> getCursosCompletadosPorIdUsuario(Long usuarioId);

    void postSolicitudInscripcionCurso(Long usuarioId,Long cursoId );

    Estado getEstadoProgresoPorIdCurso(Long cursoId);

}
