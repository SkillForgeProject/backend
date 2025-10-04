package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto;
import com.eam.skillforge.capaNegocio.servicio.OpcionRespuestaServicio;
import com.eam.skillforge.capaPersistencia.dao.OpcionPreguntaDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OpcionRespuestaServicioImpl implements OpcionRespuestaServicio {

    private final OpcionPreguntaDAO opcionPreguntaDAO;

    @Override
    public List<RespuestaCorrectaDto> getRespuestasCorrectas(Long evaluacionId) {
        log.info("Buscando ID de respuestas correctas por el ID de la evaluación: {}", evaluacionId);

        return opcionPreguntaDAO.getRespuestasCorrectas(evaluacionId);
    }
}
