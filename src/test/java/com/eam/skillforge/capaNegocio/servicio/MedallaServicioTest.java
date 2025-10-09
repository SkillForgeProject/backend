package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaPersistencia.dao.MedallaDAO;
import com.eam.skillforge.capaNegocio.servicio.impl.MedallaServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("MedallaServicio - Unit tests (service-focused)")
class MedallaServicioTest {

    @Mock
    private MedallaDAO medallaDAO;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.AprendizServicio usuarioServicio;

    private MedallaServicioImpl medallaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        medallaServicio = new MedallaServicioImpl(medallaDAO, usuarioServicio);
    }

    @Test
    void getMedallasPorUsuarioId_returnsList() {
        MedallaDto dto = new MedallaDto();
        dto.setNombre("M1");

        when(medallaDAO.buscarPorUsuarioId(1L)).thenReturn(List.of(dto));

        var result = medallaServicio.getMedallasPorUsuarioId(1L);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
