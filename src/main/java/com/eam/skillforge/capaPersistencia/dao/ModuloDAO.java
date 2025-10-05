package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaPersistencia.entidad.Modulo;
import com.eam.skillforge.capaPersistencia.mapper.ModuloMapper;
import com.eam.skillforge.capaPersistencia.repositorio.ModuloRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ModuloDAO {
    private final ModuloRepositorio moduloRepositorio;
    private final ModuloMapper moduloMapper;

    public ModuloDto guardar(ModuloDto modulo) {
        Modulo entidad = moduloMapper.toEntidad(modulo);
        Modulo entidadGuardada = moduloRepositorio.save(entidad);
        return moduloMapper.toDto(entidadGuardada);
    }
}
