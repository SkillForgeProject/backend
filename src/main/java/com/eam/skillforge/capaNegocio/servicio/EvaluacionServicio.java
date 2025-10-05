package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CreacionEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;

public interface EvaluacionServicio {

    EvaluacionDto buscarPorId(Long evaluacionId);
    void crearEvaluacion(CreacionEvaluacionDto creacionEvaluacion);
}
