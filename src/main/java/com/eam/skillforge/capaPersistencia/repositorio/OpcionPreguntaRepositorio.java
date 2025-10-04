package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto;
import com.eam.skillforge.capaPersistencia.entidad.OpcionPregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpcionPreguntaRepositorio extends JpaRepository<OpcionPregunta, Long> {

    @Query("""
        SELECT new com.eam.skillforge.capaNegocio.dto.RespuestaCorrectaDto(
            p.id,
            o.id
        )
        FROM Pregunta p
        JOIN OpcionPregunta o ON o.pregunta = p
        WHERE p.evaluacion.id = :evaluacionId
          AND o.esCorrecto = true
    """)
    List<RespuestaCorrectaDto> getRespuestasCorrectas(@Param("evaluacionId") Long evaluacionId);

}
