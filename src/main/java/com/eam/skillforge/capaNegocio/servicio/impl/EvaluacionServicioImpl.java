package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CreacionEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.OpcionPreguntaDto;
import com.eam.skillforge.capaNegocio.dto.PreguntaDto;
import com.eam.skillforge.capaNegocio.mapper.PreguntaMapper;
import com.eam.skillforge.capaNegocio.servicio.EvaluacionServicio;
import com.eam.skillforge.capaPersistencia.dao.EvaluacionDAO;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import com.eam.skillforge.capaPersistencia.entidad.OpcionPregunta;
import com.eam.skillforge.capaPersistencia.mapper.EvaluacionMapper;
import com.eam.skillforge.capaPersistencia.mapper.OpcionPreguntaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EvaluacionServicioImpl implements EvaluacionServicio {

    private final EvaluacionDAO evaluacionDAO;

    @Override
    @Transactional(readOnly = true)
    public EvaluacionDto buscarPorId(Long evaluacionId) {
        log.debug("Buscando evaluación con ID: {}", evaluacionId);
        return evaluacionDAO.buscarPorId(evaluacionId)
                .orElseThrow(() -> {
                    log.warn("Evaluación no encontrada con ID: {}", evaluacionId);
                    return new RuntimeException("Evaluación no encontrada con ID: " + evaluacionId);
                });
    }

    @Transactional
    public void crearEvaluacion(CreacionEvaluacionDto creacionEvaluacion) {
        evaluacionDAO.crearEvaluacion(creacionEvaluacion);
    }
}
