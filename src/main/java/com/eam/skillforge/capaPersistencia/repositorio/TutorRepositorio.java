package com.eam.skillforge.capaPersistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eam.skillforge.capaPersistencia.entidad.Usuario;

public interface TutorRepositorio extends JpaRepository<Usuario, Long> {

    @Query("DELETE FROM Modulo m WHERE m.id = :id")
    int eliminarModuloPorId(@Param("id") Long id);

    @Query("SELECT COUNT(m) > 0 FROM Modulo m WHERE m.id = :id")
    boolean existeModuloPorId(@Param("id") Long id);
}
