package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;
import com.eam.skillforge.capaNegocio.servicio.impl.EvaluacionServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.EvaluacionDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("EvaluacionServicio - Unit tests (service-focused)")
class EvaluacionServicioTest {

    @Mock
    private EvaluacionDAO evaluacionDAO;

    private EvaluacionServicioImpl evaluacionServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        evaluacionServicio = new EvaluacionServicioImpl(evaluacionDAO);
    }

    @Test
    void buscarPorId_returnsEvaluacion() {
        EvaluacionDto dto = new EvaluacionDto();
        when(evaluacionDAO.buscarPorId(1L)).thenReturn(Optional.of(dto));

        var result = evaluacionServicio.buscarPorId(1L);

        assertThat(result).isNotNull();
    }
}
