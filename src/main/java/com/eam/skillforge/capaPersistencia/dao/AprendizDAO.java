package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.mapper.CursoMapper;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.AprendizRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AprendizDAO {
    private final AprendizRepositorio aprendizRepositorio;
    private final UsuarioMapper aprendizMapper;
    private final CursoMapper cursoMapper;

    public Optional<UsuarioDto> buscarPorId(Long id) {
        return aprendizRepositorio.findById(id)
                .map(aprendizMapper::toDto);
    }

    public void actualizarProgresoModulo(int usuarioId, Double progreso, int moduloId) {
        aprendizRepositorio.actualizarProgresoModulo(usuarioId, progreso, moduloId);
    }

    public Double obtenerProgresoCurso(int usuarioId, int cursoId) {
        return aprendizRepositorio.obtenerProgresoPorIdCurso(usuarioId, cursoId);
    }

    public List<CursoDto>  getCursosPorIdUsuario(Long idUsuario){
        List<Curso> cursos = aprendizRepositorio.getCursosPorIdUsuario(idUsuario);
        return cursoMapper.toDTOList(cursos);

    }

    public Integer getCantidadCertificaciones(Long usuarioId) {
        return aprendizRepositorio.getCantidadCertificaciones(usuarioId);
    }

    public List<CursoDto> getCursosCompletadosPorIdUsuario(Long usuarioId) {
        return aprendizRepositorio.getCursosCompletadosPorIdUsuario(usuarioId);
    }
}
