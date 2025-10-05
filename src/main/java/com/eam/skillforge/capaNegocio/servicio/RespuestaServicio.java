package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.EnvioEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import org.springframework.stereotype.Service;

public interface RespuestaServicio {
    RespuestaDto crearRespuesta(EnvioEvaluacionDto respuestas);
}
