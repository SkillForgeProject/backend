package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.InscripcionDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.Inscripcion;
import com.eam.skillforge.capaPersistencia.mapper.CursoMapper;
import com.eam.skillforge.capaPersistencia.mapper.InscripcionMapper;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.CursoRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.InscripcionRepositorio;
import com.eam.skillforge.capaPersistencia.repositorio.TutorRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TutorDAO {
    private final TutorRepositorio tutorRepositorio;
    private final CursoRepositorio cursoRepositorio;
    private final InscripcionRepositorio inscripcionRepositorio;
    private final UsuarioMapper tutorMapper;
    private final CursoMapper cursoMapper;
    private final InscripcionMapper inscripcionMapper;

    public List<CursoDto> getCursosPorTutorId(Long tutorId) {
        List<Curso> entidades = cursoRepositorio.findByTutorId(tutorId);
        return cursoMapper.toDTOList(entidades);
    }

    public Optional<UsuarioDto> getTutorPorId(Long tutorId) {
        return tutorRepositorio.findById(tutorId)
                .map(tutorMapper::toDto);
    }

    public CursoDto crearCurso(CursoDto curso) {
        Curso entidad = cursoMapper.toEntidad(curso);
        Curso guardada = cursoRepositorio.save(entidad);
        return cursoMapper.toDTO(guardada);
    }

    public InscripcionDto inscribir(InscripcionDto inscripcion) {
        Inscripcion entidad = inscripcionMapper.toEntidad(inscripcion);
        Inscripcion guardada = inscripcionRepositorio.save(entidad);
        return inscripcionMapper.toDto(guardada);
    }
}
