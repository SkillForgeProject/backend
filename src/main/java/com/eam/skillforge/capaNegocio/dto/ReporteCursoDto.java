package com.eam.skillforge.capaNegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteCursoDto {

    private Long cursoId;

    private String titulo;

    private Long totalInscritos;

    private Long totalCompletados;

    private Double tasaCompletados;

    private Double puntuacionPromedio;

    private Long duracionEstim;

    private Boolean isActivo;

    // Constructor requerido por la expresión constructor JPQL
    // Firma: (Long cursoId, String titulo, Long totalInscritos, Long totalCompletados, Double puntuacionPromedio, Long duracionEstim, Boolean isActivo)
    public ReporteCursoDto(Long cursoId, String titulo, Long totalInscritos, Long totalCompletados, Double puntuacionPromedio, Long duracionEstim, Boolean isActivo) {
        this.cursoId = cursoId;
        this.titulo = titulo;
        this.totalInscritos = totalInscritos;
        this.totalCompletados = totalCompletados;
        this.puntuacionPromedio = puntuacionPromedio;
        this.duracionEstim = duracionEstim;
        this.isActivo = isActivo;
        // Calculamos la tasa en Java para evitar problemas de precisión/compatibilidad en JPQL
        if (this.totalInscritos == null || this.totalInscritos == 0L) {
            this.tasaCompletados = null;
        } else {
            this.tasaCompletados = (this.totalCompletados == null ? 0.0 : (this.totalCompletados * 100.0) / this.totalInscritos);
        }
    }
}
