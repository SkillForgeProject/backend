package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaNegocio.dto.ReporteCursoDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReporteCursoRepositorio extends JpaRepository<Curso, Long> {

    @Query(
            value = """
    SELECT
      CURS.id as curso_id,
      CURS.titulo as titulo,
      COUNT(INS.id) as total_inscritos,
      SUM(CASE WHEN INS.estado = 3 THEN 1 ELSE 0 END) as total_completados,
      AVG(CURS_PUNT.puntuacion) as puntuacion_promedio,
      CURS.duracionEstim as duracion_estim,
      CURS.isActivo as is_activo
    FROM curso CURS
    LEFT JOIN inscripcion INS
      ON INS.cursoId = CURS.id
      AND (:desde IS NULL OR INS.fechaInscripcion >= :desde)
      AND (:hasta IS NULL OR INS.fechaInscripcion <= :hasta)
    LEFT JOIN cursos_puntuacion CURS_PUNT
      ON CURS_PUNT.cursoId = CURS.id
    WHERE (:categoriaId IS NULL OR CURS.nivel = :categoriaId)
      AND (:tutorId IS NULL OR CURS.id_tutor = :tutorId)
    GROUP BY CURS.id, CURS.titulo, CURS.duracionEstim, CURS.isActivo
    """,
            nativeQuery = true,
            countQuery = """
    SELECT COUNT(DISTINCT CURS.id)
    FROM curso CURS
    LEFT JOIN inscripcion INS
      ON INS.cursoId = CURS.id
      AND (:desde IS NULL OR INS.fechaInscripcion >= :desde)
      AND (:hasta IS NULL OR INS.fechaInscripcion <= :hasta)
    WHERE (:categoriaId IS NULL OR CURS.nivel = :categoriaId)
      AND (:tutorId IS NULL OR CURS.id_tutor = :tutorId)
    """
    )
    Page<Object[]> getRendimientoCursos(
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta,
            @Param("categoriaId") Long categoriaId,
            @Param("tutorId") Long tutorId,
            Pageable pageable
    );
}
