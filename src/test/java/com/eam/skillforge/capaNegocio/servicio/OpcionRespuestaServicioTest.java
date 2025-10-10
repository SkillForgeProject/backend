package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto;
import com.eam.skillforge.capaNegocio.servicio.impl.OpcionRespuestaServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.OpcionPreguntaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("OpcionRespuestaServicio - Unit tests (service-focused)")
class OpcionRespuestaServicioTest {

    @Mock
    private OpcionPreguntaDAO opcionPreguntaDAO;

    private OpcionRespuestaServicioImpl servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicio = new OpcionRespuestaServicioImpl(opcionPreguntaDAO);
    }

    @Test
    void getRespuestasCorrectas_returnsList() {
        RespuestaCorrectaDto r = new RespuestaCorrectaDto();
        when(opcionPreguntaDAO.getRespuestasCorrectas(1L)).thenReturn(List.of(r));

        var result = servicio.getRespuestasCorrectas(1L);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
