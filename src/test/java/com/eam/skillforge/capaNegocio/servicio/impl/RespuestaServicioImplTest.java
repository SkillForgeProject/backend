package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.EnvioEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import com.eam.skillforge.capaPersistencia.dao.RespuestaDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RespuestaServicioImplTest {

    @Mock
    private RespuestaDAO respuestaDAO;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.UsuarioServicio usuarioServicio;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.EvaluacionServicio evaluacionServicio;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.OpcionRespuestaServicio opcionRespuestaServicio;

    @InjectMocks
    private RespuestaServicioImpl respuestaServicio;

    @Test
    void crearRespuesta_delegatesToDao() {
        EnvioEvaluacionDto envio = new EnvioEvaluacionDto(1L, 1L, java.util.List.of(2L), java.util.List.of(1L));

        // stub usuario and evaluacion existence checks
        org.mockito.Mockito.when(usuarioServicio.buscarPorId(1L)).thenReturn(new com.eam.skillforge.capaNegocio.dto.UsuarioDto(1L, "p", "n", "e@e.com", null, null));
    org.mockito.Mockito.when(evaluacionServicio.buscarPorId(1L)).thenReturn(new com.eam.skillforge.capaNegocio.dto.EvaluacionDto(1, 1L, "E", 1L, 100));

        // stub respuestas correctas: preguntaId -> opcionCorrectaId
        java.util.List<com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto> correctas = java.util.List.of(new com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto(2L, 1L));
        org.mockito.Mockito.when(opcionRespuestaServicio.getRespuestasCorrectas(1L)).thenReturn(correctas);

        RespuestaDto saved = new RespuestaDto();
        saved.setPuntuacion(100f);

    when(respuestaDAO.guardar(org.mockito.ArgumentMatchers.any(RespuestaDto.class))).thenReturn(saved);

    var result = respuestaServicio.crearRespuesta(envio);
        assertThat(result).isNotNull();
        assertThat(result.getPuntuacion()).isEqualTo(100f);
    }
}
