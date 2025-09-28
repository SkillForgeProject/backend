package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaNegocio.dto.UsuariosPorCursoMesDTO;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.idRol.id = :rolId")
    long contarPorRol(@Param("rolId") Long rolId);
    @Query("SELECT AVG(DISTINCT i.progreso) " +
            "FROM Inscripcion i " +
            "JOIN i.curso c " +
            "JOIN Modulo m ON m.curso = c " +
            "WHERE i.estado.id <> 1")
    Double findPromedioProgresoDistinto();

    @Query(value = "SELECT c.titulo AS titulo, " +
            "MONTHNAME(i.fechaInscripcion) AS mes, " +
            "COUNT(DISTINCT i.usuarioId) AS totalUsuarios " +
            "FROM inscripcion i " +
            "INNER JOIN curso c ON i.cursoId = c.id " +
            "GROUP BY c.titulo, MONTHNAME(i.fechaInscripcion)",
            nativeQuery = true)
    List<UsuariosPorCursoMesDTO> contarUsuariosPorCursoYMes();
}

