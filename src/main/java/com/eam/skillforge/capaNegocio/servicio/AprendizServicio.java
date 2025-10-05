package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;

import java.util.List;


public interface AprendizServicio {

    UsuarioDto getUsuarioPorId(Long id);
    void putProgresoModulo(int usuarioId,Double progreso, int moduloId);
    Double getProgresoCurso(int usuarioId, int cursoId);

    List<CursoDto> getCursosPorIdUsuario(Long idUsuario);

}
