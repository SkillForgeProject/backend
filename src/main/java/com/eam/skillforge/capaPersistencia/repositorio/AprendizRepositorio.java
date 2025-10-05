package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AprendizRepositorio extends JpaRepository<Usuario, Long> {

    @Query(value = "UPDATE inscripcion SET progreso=:progreso WHERE moduloId=:moduloId AND usuarioId=:usuarioId", nativeQuery = true)
    void actualizarProgresoModulo(@Param("usuarioId") int usuarioId,
                                  @Param("progreso") Double progreso,
                                  @Param("moduloId") int moduloId
                                  );
    @Query("SELECT AVG(COALESCE(i.progreso, 0)) " +
            "FROM Modulo m " +
            "LEFT JOIN Inscripcion i " +
            "ON m.id = i.modulo.id " +
            "AND m.curso.id = i.curso.id " +
            "AND i.usuario.id = :usuarioId " +
            "WHERE m.curso.id = :cursoId")
    Double obtenerProgresoPorIdCurso(@Param("usuarioId") int usuarioId,@Param("cursoId") int cursoId);

    Optional<Usuario> findByEmail(String email);

    @Query(value = """
        SELECT c.*
        FROM curso c
        INNER JOIN inscripcion i ON c.id = i.cursoId
        WHERE i.usuarioId = :usuarioId
        """, nativeQuery = true)
    List<Curso> getCursosPorIdUsuario(Long usuarioId);

    @Query("SELECT COUNT(c) FROM Certificado c WHERE c.usuario.id = :usuarioId")
    Integer getCantidadCertificaciones(@Param("usuarioId") Long usuarioId);

}
