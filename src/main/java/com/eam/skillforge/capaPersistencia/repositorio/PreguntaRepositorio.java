package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {
    
    @Modifying
    @Query(value = "INSERT INTO pregunta (id_evaluacion, pregunta) " +
                   "VALUES (:evaluacionId, :pregunta)",
           nativeQuery = true)
    void insertarPreguntaNativa(@Param("pregunta") String pregunta,
                               @Param("evaluacionId") Long evaluacionId);
    
    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long obtenerUltimoIdInsertado();
}