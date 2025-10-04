package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto;

import java.util.List;

public interface OpcionRespuestaServicio {
    List<RespuestaCorrectaDto> getRespuestasCorrectas(Long evaluacionId);
}
