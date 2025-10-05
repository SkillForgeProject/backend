package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepositorio extends JpaRepository<Modulo, Long> {

    @Query(value = "DELETE FROM inscripcion WHERE moduloId = :id", nativeQuery = true)
    void eliminarInscripcionesPorModulo(@Param("id") Long id);

    @Query(value = "DELETE FROM modulo WHERE id = :id", nativeQuery = true)
    void eliminarModuloPorId(@Param("id") Long id);

    @Query( value = "DELETE FROM evaluacion WHERE moduloId = :id", nativeQuery = true)
    void eliminarEvaluacionPorModulo(@Param("id") Long id);

    @Query( value = """
            DELETE RES 
            FROM respuesta RES
            INNER JOIN evaluacion EV ON RES.evaluacionId = EV.id
            WHERE EV.moduloId = :id
            """, nativeQuery = true)
    void eliminarRespuestaPorModulo(@Param("id") Long id);

    @Query("SELECT COUNT(m) > 0 FROM Modulo m WHERE m.id = :id")
    boolean existeModuloPorId(@Param("id") Long id);

}

