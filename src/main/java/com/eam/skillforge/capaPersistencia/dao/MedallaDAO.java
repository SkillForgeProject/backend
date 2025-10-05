package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaPersistencia.entidad.Medalla;
import com.eam.skillforge.capaPersistencia.mapper.MedallaMapper;
import com.eam.skillforge.capaPersistencia.repositorio.MedallaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MedallaDAO {
    private final MedallaRepositorio medallaRepositorio;
    private final MedallaMapper medallaMapper;

    public MedallaDto guardar(MedallaDto medallaDto) {
        Medalla entidad = medallaMapper.toEntidad(medallaDto);
        Medalla entidadGuardada = medallaRepositorio.save(entidad);
        return medallaMapper.toDto(entidadGuardada);
    }

    public List<MedallaDto> buscarPorUsuarioId(Long usuarioId) {
        List<Medalla> entidades = medallaRepositorio.findByUsuarioId(usuarioId);

        return medallaMapper.toDtoList(entidades);
    }
}
