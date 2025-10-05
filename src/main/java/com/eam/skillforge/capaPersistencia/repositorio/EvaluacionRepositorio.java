package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepositorio extends JpaRepository<Evaluacion, Long> {

    @Query(value = "INSERT INTO evaluacion (titulo, moduloId, id_tipo_evaluacion, puntajeMax) " +
            "VALUES (:titulo, :moduloId, :id_tipo_evaluacion, :puntajeMax)",
            nativeQuery = true)
    void insertarEvaluacionNativa(@Param("titulo") String titulo,
                                  @Param("moduloId") Long moduloId,
                                  @Param("id_tipo_evaluacion") Long id_tipo_evaluacion,
                                  @Param("puntajeMax") Integer puntaMax);
    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long obtenerUltimoIdInsertado();
}
