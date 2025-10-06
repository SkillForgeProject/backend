package com.eam.skillforge.capaNegocio.servicio;

import com.eam.skillforge.capaNegocio.dto.ReporteCursoDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface ReporteServicio {
    Page<ReporteCursoDto> getRendimientoCursos(
            LocalDate fechaDesde, LocalDate fechaHasta, Long categoriaId, Long tutorId, int pagina, int tamano);
}
