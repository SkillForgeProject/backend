package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.TopCursoDto;
import com.eam.skillforge.capaNegocio.servicio.impl.CursoServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
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
@DisplayName("CursoServicio - Unit tests (service-focused)")
class CursoServicioTest {

    @Mock
    private CursoDAO cursoDAO;

    private CursoServicioImpl cursoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoServicio = new CursoServicioImpl(cursoDAO);
    }

    @Test
    void getTopCursosMejoresPuntuados_returnsList() {
        TopCursoDto t = new TopCursoDto();
        when(cursoDAO.getTopCursosMejoresPuntuados()).thenReturn(List.of(t));

        var result = cursoServicio.getTopCursosMejoresPuntuados();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
