package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.ReporteCursoDto;
import com.eam.skillforge.capaPersistencia.repositorio.ReporteCursoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class ReporteCursoDAO {

    private final ReporteCursoRepositorio reporteCursoRepositorio;

    public Page<ReporteCursoDto> getReporteCurso(
            LocalDate desde, LocalDate hasta, Long categoriaId, Long tutorId, int pagina, int tamano) {

        Pageable pageable = PageRequest.of(pagina, tamano);
        Page<Object[]> resultados = reporteCursoRepositorio.getRendimientoCursos(desde, hasta, categoriaId, tutorId, pageable);

        // Mapear cada Object[] a ReporteCursoDto
        Page<ReporteCursoDto> paginaDto = resultados.map(row -> {
            // Columnas del select nativo: curso_id, titulo, total_inscritos, total_completados, puntuacion_promedio, duracion_estim, is_activo
            Long cursoId = row[0] != null ? ((Number) row[0]).longValue() : null;
            String titulo = row[1] != null ? row[1].toString() : null;
            Long totalInscritos = row[2] != null ? ((Number) row[2]).longValue() : 0L;
            Long totalCompletados = row[3] != null ? ((Number) row[3]).longValue() : 0L;
            Double puntuacionPromedio = row[4] != null ? ((Number) row[4]).doubleValue() : null;
            Long duracionEstim = row[5] != null ? ((Number) row[5]).longValue() : null;
            Boolean isActivo = row[6] != null ? ((Boolean) row[6]) : null;

            return new ReporteCursoDto(cursoId, titulo, totalInscritos, totalCompletados, puntuacionPromedio, duracionEstim, isActivo);
        });

        return paginaDto;
    }

}
