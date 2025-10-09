package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.impl.UsuarioServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.UsuarioDAO;
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
@DisplayName("UsuarioServicio - Unit tests (service-focused)")
class UsuarioServicioTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    @Mock
    private CursoServicio cursoServicio;

    @Mock
    private ModuloRecursoServicio moduloRecursoServicio;

    private UsuarioServicioImpl servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicio = new UsuarioServicioImpl(usuarioDAO, cursoServicio, moduloRecursoServicio);
    }

    @Test
    void getUsuarioPorCorreo_returnsUsuario() {
        UsuarioDto u = new UsuarioDto();
        u.setEmail("a@b.com");
        when(usuarioDAO.buscarPorCorreo("a@b.com")).thenReturn(u);

        var result = servicio.getUsuarioPorCorreo("a@b.com");

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("a@b.com");
    }

    @Test
    void getRecursosPorModulo_delegatesToModuloRecursoServicio() {
        ModuloRecursoDto r = new ModuloRecursoDto();
        when(moduloRecursoServicio.getRecursosPorModulo(1L)).thenReturn(List.of(r));

        var result = servicio.getRecursosPorModulo(1L);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
