package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import com.eam.skillforge.capaPersistencia.entidad.ModuloRecurso;
import com.eam.skillforge.capaPersistencia.mapper.ModuloRecursoMapper;
import com.eam.skillforge.capaPersistencia.repositorio.ModuloRecursoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ModuloRecursoDAO {

    private final ModuloRecursoRepositorio moduloRecursoRepositorio;
    private final ModuloRecursoMapper moduloRecursoMapper;

    public ModuloRecursoDto guardarRecurso(ModuloRecursoDto recurso) {
        ModuloRecurso entidad = moduloRecursoMapper.toEntidad(recurso);
        ModuloRecurso guardada = moduloRecursoRepositorio.save(entidad);
        return moduloRecursoMapper.toDto(guardada);
    }

    public List<ModuloRecursoDto> getRecursosPorModulo(Long moduloId) {
        List<ModuloRecurso> entidades = moduloRecursoRepositorio.findByModulo_Id(moduloId);
        return moduloRecursoMapper.toDtoList(entidades);
    }
}
