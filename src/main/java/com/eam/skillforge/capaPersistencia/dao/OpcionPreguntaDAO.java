package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto;
import com.eam.skillforge.capaPersistencia.mapper.OpcionPreguntaMapper;
import com.eam.skillforge.capaPersistencia.repositorio.OpcionPreguntaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpcionPreguntaDAO {
    private final OpcionPreguntaRepositorio opcionPreguntaRepositorio;
    private final OpcionPreguntaMapper opcionPreguntaMapper;

    public List<RespuestaCorrectaDto> getRespuestasCorrectas(Long evaluacionId) {

        return opcionPreguntaRepositorio.getRespuestasCorrectas(evaluacionId);
    }
}
