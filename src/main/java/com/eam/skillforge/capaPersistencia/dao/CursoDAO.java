package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.*;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import com.eam.skillforge.capaPersistencia.mapper.CursoMapper;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.CursoRepositorio;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.eam.skillforge.capaNegocio.excepciones.CursoNoEncontradoExcepcion;

@Repository
@RequiredArgsConstructor
public class CursoDAO {
    private final CursoRepositorio cursoRepositorio;
    private final CursoMapper cursoMapper;
    private final UsuarioMapper usuarioMapper;

    public Optional<CursoDto> buscarPorId(Long id) {
        return cursoRepositorio.findById(id)
                .map(cursoMapper::toDTO);
    }

    public boolean eliminarPorId(Long cursoId) {
        if(cursoRepositorio.existsById(cursoId)) {
            cursoRepositorio.deleteById(cursoId);
            return true;
        }

        return false;
    }

    public Optional<List<CursoDto>> buscarPorNivel(Long nivelId) {
        List<Curso> cursos = cursoRepositorio.findByNivel(nivelId);
        return cursos.isEmpty()
                ? Optional.empty()
                : Optional.of(cursoMapper.toDTOList(cursos));
    }

    public Optional<CursoDto> actualizar(Long id, CursoDto curso) {
        return cursoRepositorio.findById(id)
                .map(entidadExistente -> {
                    cursoMapper.actualizarEntidadDesdeDto(curso, entidadExistente);
                    Curso entidadActualizada = cursoRepositorio.save(entidadExistente);
                    return cursoMapper.toDTO(entidadActualizada);
                });
    }

    public List<TopCursoDto> getTopCursosMejoresPuntuados() {
        List<Object[]> resultados = cursoRepositorio.findTopCursosMejoresPuntuados();
        
        return resultados.stream()
                .map(resultado -> new TopCursoDto(
                        ((Number) resultado[0]).longValue(),  // id
                        (String) resultado[1],                // titulo
                        ((Number) resultado[2]).doubleValue() // puntuacion
                ))
                .collect(Collectors.toList());
    }

    public List<CursoDiferenciaUsuariosDto> getCursosMasTomados() {
        List<Object[]> resultados = cursoRepositorio.findCursosMasTomados();
        
        return resultados.stream()
                .map(resultado -> new CursoDiferenciaUsuariosDto(
                        ((Number) resultado[0]).intValue(),   // diferenciaUsuarios
                        ((Number) resultado[1]).longValue(),  // cursoId
                        (String) resultado[2]                 // titulo
                ))
                .collect(Collectors.toList());
    }

    public List<UsuarioDto> getCursosPorInscripcion(Long cursoId) {
        
        List<Usuario> usuarios = cursoRepositorio.findUsuariosPorCursoId(cursoId);
        
        if (usuarios.isEmpty()) {
            throw new CursoNoEncontradoExcepcion("No se encontraron cursos para el ID: " + cursoId);
        }
        
        List<UsuarioDto> usuariosDto = usuarioMapper.toDTOList(usuarios);
        
        return usuariosDto;
    }

    public List<CursoDto> getCursosActivos(){
        List<Curso> cursosActivos = cursoRepositorio.getCursosActivos();
        return cursoMapper.toDTOList(cursosActivos);
    }

    public List<CursoDto> getCursosPorIdUsuario(Long idUsuario){
        List<Curso> cursosAprendiz = cursoRepositorio.getCursosPorIdUsuario(idUsuario);
        return cursoMapper.toDTOList(cursosAprendiz);
    }

}
