package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaNegocio.servicio.impl.ModuloServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.ModuloDAO;
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
@DisplayName("ModuloServicio - Unit tests (service-focused)")
class ModuloServicioTest {

    @Mock
    private ModuloDAO moduloDAO;

    private ModuloServicioImpl servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicio = new ModuloServicioImpl(moduloDAO);
    }

    @Test
    void getModuloPorId_returnsModulo() {
        ModuloDto m = new ModuloDto();
        when(moduloDAO.buscarPorId(1L)).thenReturn(Optional.of(m));

        var result = servicio.getModuloPorId(1L);

        assertThat(result).isNotNull();
    }
}
