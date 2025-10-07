package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.ReporteCursoDto;
import com.eam.skillforge.capaNegocio.servicio.ReporteServicio;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import com.eam.skillforge.capaPersistencia.dao.ReporteCursoDAO;
import com.eam.skillforge.capaPersistencia.repositorio.CursoRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReporteServicioImpl implements ReporteServicio {

    private final ReporteCursoDAO reporteCursoDAO;

    @Override
    public Page<ReporteCursoDto> getRendimientoCursos(
            LocalDate fechaDesde, LocalDate fechaHasta, Long categoriaId, Long tutorId, int pagina, int tamano) {
        return reporteCursoDAO.getReporteCurso(fechaDesde, fechaHasta, categoriaId, tutorId, pagina, tamano);
    }
}
