package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.dao.AdministradorDAO;
import com.eam.skillforge.capaPersistencia.repositorio.AdministradorRepositorio;
import com.eam.skillforge.capaNegocio.servicio.impl.AdministradorServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("AdministradorServicio - Unit tests (service-focused)")
class AdministradorServicioTest {

    @Mock
    private AdministradorDAO administradorDAO;

    @Mock
    private AdministradorRepositorio administradorRepositorio;

    private AdministradorServicioImpl administradorServicio; // we test service behavior but test class name avoids Impl suffix

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // inject repository mock into private field
        // construct service manually so constructor-injected final fields are set from mocks
        administradorServicio = new AdministradorServicioImpl(administradorDAO);
        ReflectionTestUtils.setField(administradorServicio, "usuarioRepository", administradorRepositorio);
    }

    @Test
    void postUsuario_valid_delegatesToDao() {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(1L);
        dto.setNombre("Admin");
        dto.setContrasena("pwd");
        dto.setEmail("admin@test.com");
        dto.setId_rol(new com.eam.skillforge.capaPersistencia.entidad.Rol(1L, "ADMIN"));
        dto.setDepartamento(new com.eam.skillforge.capaPersistencia.entidad.Departamento(1L, "DEP"));

        doReturn(false).when(administradorRepositorio).existsByEmail(ArgumentMatchers.anyString());
        when(administradorDAO.guardarUsuario(ArgumentMatchers.any())).thenReturn(dto);

        var created = administradorServicio.postUsuario(dto);

        assertThat(created).isNotNull();
        assertThat(created.getNombre()).isEqualTo("Admin");
    }
}
