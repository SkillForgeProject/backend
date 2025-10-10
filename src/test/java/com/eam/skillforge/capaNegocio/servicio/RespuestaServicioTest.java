package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.EnvioEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import com.eam.skillforge.capaPersistencia.dao.RespuestaDAO;
import com.eam.skillforge.capaNegocio.servicio.impl.RespuestaServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@DisplayName("RespuestaServicio - Unit tests (service-focused)")
class RespuestaServicioTest {

    @Mock
    private RespuestaDAO respuestaDAO;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.EvaluacionServicio evaluacionServicio;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.UsuarioServicio usuarioServicio;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.OpcionRespuestaServicio opcionRespuestaServicio;

    private RespuestaServicioImpl respuestaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        respuestaServicio = new RespuestaServicioImpl(respuestaDAO, evaluacionServicio, usuarioServicio, opcionRespuestaServicio, null);
    }

    @Test
    void crearRespuesta_delegatesToDao_and_calculatesScore() {
        EnvioEvaluacionDto envio = new EnvioEvaluacionDto(1L, 1L, List.of(2L), List.of(2L));

        RespuestaDto saved = new RespuestaDto();
        saved.setPuntuacion(100f);

        // mock dependencies used in calcularPuntuacion and validarDataRespuestas
        doReturn(null).when(usuarioServicio).buscarPorId(ArgumentMatchers.anyLong());
        doReturn(null).when(evaluacionServicio).buscarPorId(ArgumentMatchers.anyLong());
        when(opcionRespuestaServicio.getRespuestasCorrectas(1L)).thenReturn(List.of(new com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto(1L,2L)));
        when(respuestaDAO.guardar(ArgumentMatchers.any())).thenReturn(saved);

        var result = respuestaServicio.crearRespuesta(envio);

        assertThat(result).isNotNull();
        assertThat(result.getPuntuacion()).isEqualTo(100f);
    }
}
