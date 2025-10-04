package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import com.eam.skillforge.capaPersistencia.entidad.Respuesta;
import com.eam.skillforge.capaPersistencia.mapper.RespuestaMapper;
import com.eam.skillforge.capaPersistencia.repositorio.RespuestaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RespuestaDAO {

    private final RespuestaRepositorio respuestaRepositorio;
    private final RespuestaMapper respuestaMapper;

    public RespuestaDto guardar(RespuestaDto respuesta) {
        Respuesta entidad = respuestaMapper.toEntidad(respuesta);
        Respuesta entidadGuardada = respuestaRepositorio.save(entidad);
        return respuestaMapper.toDto(entidadGuardada);
    }
}
