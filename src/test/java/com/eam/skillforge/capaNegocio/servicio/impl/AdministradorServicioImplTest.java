package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.dao.AdministradorDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AdministradorServicioImplTest {

    @Mock
    private AdministradorDAO administradorDAO;

    @Mock
    private com.eam.skillforge.capaPersistencia.repositorio.AdministradorRepositorio administradorRepositorio;

    @InjectMocks
    private AdministradorServicioImpl administradorServicio;

    @Test
    void postUsuario_callsDao() {
        UsuarioDto dto = new UsuarioDto();
        dto.setNombre("Admin");
        dto.setContrasena("secret");
    // inject repository mock into the service instance used by tests and stub existsByEmail
    org.springframework.test.util.ReflectionTestUtils.setField(administradorServicio, "usuarioRepository", administradorRepositorio);
    org.mockito.Mockito.doReturn(false).when(administradorRepositorio).existsByEmail(org.mockito.ArgumentMatchers.anyString());
    dto.setEmail("admin@test.com");
    dto.setId_rol(new com.eam.skillforge.capaPersistencia.entidad.Rol(1L, "ADMIN"));
    dto.setDepartamento(new com.eam.skillforge.capaPersistencia.entidad.Departamento(1L, "DEP"));

    when(administradorDAO.guardarUsuario(org.mockito.ArgumentMatchers.any())).thenReturn(dto);

    var result = administradorServicio.postUsuario(dto);
        assertThat(result).isNotNull();
        assertThat(result.getNombre()).isEqualTo("Admin");
    }
}
