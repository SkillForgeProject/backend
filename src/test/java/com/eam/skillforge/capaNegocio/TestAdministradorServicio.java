package com.eam.skillforge.capaNegocio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.impl.AdministradorServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.AdministradorDAO;
import com.eam.skillforge.capaPersistencia.entidad.Departamento;
import com.eam.skillforge.capaPersistencia.entidad.Rol;
import com.eam.skillforge.capaPersistencia.repositorio.UsuarioRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("AdministradorServicio - Tests Unitarios")
public class TestAdministradorServicio {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private AdministradorDAO administradorDAO;

    @InjectMocks
    private AdministradorServicioImpl administradorServicio;

    // Objetos para pruebas
    private UsuarioDto usuarioValidoDto;
    private Long usuarioIdValido;


    @BeforeEach
    void setUp() {
        usuarioIdValido = 1L;
        usuarioValidoDto = new UsuarioDto(
                1L,
                "asdf",
                "Prueba",
                "prueba@prueba.com",
                new Rol(),
                new Departamento()
        );
        // inicializar variables dinámicamente
        MockitoAnnotations.openMocks(this);
    }




    // assertThrow

    @Test
    @DisplayName("POST - Usuario válido debe retornar Usuario deado con ID")
    void postUsuario_ShouldReturnCreatedUsuario() {
        // ARANGE (Given) - Perar el escenario
        UsuarioDto expectedUsuario = new UsuarioDto(
                usuarioValidoDto.getId(),
                usuarioValidoDto.getContrasena(),
                usuarioValidoDto.getNombre(),
                usuarioValidoDto.getEmail(),
                usuarioValidoDto.getId_rol(),
                usuarioValidoDto.getDepartamento()
        );

        // Mock del comportamiento
        when(administradorDAO.guardarUsuario(expectedUsuario));

        assertThat()
    }



}









