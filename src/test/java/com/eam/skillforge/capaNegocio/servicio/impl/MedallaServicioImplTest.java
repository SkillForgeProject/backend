package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaPersistencia.dao.MedallaDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedallaServicioImplTest {

    @Mock
    private MedallaDAO medallaDAO;

    @Mock
    private com.eam.skillforge.capaNegocio.servicio.AprendizServicio usuarioServicio;

    @InjectMocks
    private MedallaServicioImpl medallaServicio;

    @Test
    void getMedallas_returnsList() {
        MedallaDto dto = new MedallaDto();
        dto.setNombre("M");
    when(usuarioServicio.getUsuarioPorId(1L)).thenReturn(new com.eam.skillforge.capaNegocio.dto.UsuarioDto(1L, "p", "n", "e@e.com", null, null));
    when(medallaDAO.buscarPorUsuarioId(1L)).thenReturn(List.of(dto));

    var result = medallaServicio.getMedallasPorUsuarioId(1L);
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
