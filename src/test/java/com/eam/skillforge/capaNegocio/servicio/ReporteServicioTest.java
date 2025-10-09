package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ReporteCursoDto;
import com.eam.skillforge.capaPersistencia.dao.ReporteCursoDAO;
import com.eam.skillforge.capaNegocio.servicio.impl.ReporteServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReporteServicio - Unit tests (service-focused)")
class ReporteServicioTest {

    @Mock
    private ReporteCursoDAO reporteDAO;

    private ReporteServicioImpl reporteServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reporteServicio = new ReporteServicioImpl(reporteDAO);
    }

    @Test
    void getRendimientoCursos_returnsPage() {
        // use the constructor implemented in ReporteCursoDto
        ReporteCursoDto dto = new ReporteCursoDto(1L, "Curso A", 10L, 5L, 4.5, 120L, true);
        Page<ReporteCursoDto> page = new PageImpl<>(List.of(dto), PageRequest.of(0,10), 1);

        when(reporteDAO.getReporteCurso(org.mockito.ArgumentMatchers.isNull(), org.mockito.ArgumentMatchers.isNull(), org.mockito.ArgumentMatchers.isNull(), org.mockito.ArgumentMatchers.isNull(), org.mockito.ArgumentMatchers.anyInt(), org.mockito.ArgumentMatchers.anyInt())).thenReturn(page);

        var result = reporteServicio.getRendimientoCursos(null, null, null, null, 0, 10);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1L);
    }
}
