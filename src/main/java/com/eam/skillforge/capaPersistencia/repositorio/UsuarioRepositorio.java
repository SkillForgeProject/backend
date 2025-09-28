package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.idRol.id = :rolId")
    long contarPorRol(@Param("rolId") Long rolId);
}