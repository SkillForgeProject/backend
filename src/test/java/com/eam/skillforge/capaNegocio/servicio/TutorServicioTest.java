package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.impl.TutorServicioImpl;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import com.eam.skillforge.capaPersistencia.dao.ModuloDAO;
import com.eam.skillforge.capaPersistencia.dao.TutorDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("TutorServicio - Unit tests (service-focused)")
class TutorServicioTest {

    @Mock
    private TutorDAO tutorDAO;

    @Mock
    private CursoDAO cursoDAO;

    @Mock
    private ModuloDAO moduloDAO;

    @Mock
    private CursoServicio cursoServicio;

    @Mock
    private ModuloServicio moduloServicio;

    @Mock
    private UsuarioServicio usuarioServicio;

    @Mock
    private ModuloRecursoServicio moduloRecursoServicio;

    private TutorServicioImpl servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicio = new TutorServicioImpl(tutorDAO, cursoDAO, moduloDAO, cursoServicio, moduloServicio, usuarioServicio, moduloRecursoServicio);
    }

    @Test
    void getCursosPorTutorId_returnsList() {
        CursoDto c = new CursoDto();
        when(tutorDAO.getCursosPorTutorId(2L)).thenReturn(List.of(c));
        when(tutorDAO.getTutorPorId(2L)).thenReturn(Optional.of(new UsuarioDto()));

        var result = servicio.getCursosPorTutorId(2L);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }
}
