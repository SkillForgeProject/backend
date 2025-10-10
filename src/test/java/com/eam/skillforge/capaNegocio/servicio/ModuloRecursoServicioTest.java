package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import com.eam.skillforge.capaNegocio.servicio.impl.ModuloRecursoServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.ModuloRecursoDAO;
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
@DisplayName("ModuloRecursoServicio - Unit tests (service-focused)")
class ModuloRecursoServicioTest {

    @Mock
    private ModuloRecursoDAO moduloRecursoDAO;

    private ModuloRecursoServicioImpl servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicio = new ModuloRecursoServicioImpl(moduloRecursoDAO);
    }

    @Test
    void getRecursosPorModulo_returnsList() {
        ModuloRecursoDto r = new ModuloRecursoDto();
        when(moduloRecursoDAO.getRecursosPorModulo(1L)).thenReturn(List.of(r));

        var result = servicio.getRecursosPorModulo(1L);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
