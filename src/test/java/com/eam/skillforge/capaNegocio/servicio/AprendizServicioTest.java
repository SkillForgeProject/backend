package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.impl.AprendizServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.AprendizDAO;
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
@DisplayName("AprendizServicio - Unit tests (service-focused)")
class AprendizServicioTest {

    @Mock
    private AprendizDAO aprendizDAO;

    private AprendizServicioImpl aprendizServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aprendizServicio = new AprendizServicioImpl(aprendizDAO);
    }

    @Test
    void getUsuarioPorId_returnsUsuario() {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setId(1L);
        usuario.setEmail("test@example.com");

        when(aprendizDAO.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        var result = aprendizServicio.getUsuarioPorId(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("test@example.com");
    }
}
