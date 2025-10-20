package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.EstadoSolicitud;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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


    @Query(value = """
            SELECT DISTINCT i.curso
            FROM Inscripcion i
            WHERE i.estado.id = 4
            AND i.usuario.id = :usuarioId
            """)
    List<CursoDto> getCursosCompletadosPorIdUsuario(@Param("usuarioId") Long usuarioId);

    @Query(value = """
            INSERT INTO Solicitud (usuarioId, cursoId, estadoSolicitud)
            VALUES (:usuarioId, :cursoId, 
            (SELECT id FROM EstadoSolicitud WHERE estado = 'ENESPERA'))
            """, nativeQuery = true)
    void postSolicitudInscripcionCurso(@Param("usuarioId") Long usuarioId, @Param("cursoId") Long cursoId);

    @Query("""
        SELECT CASE
            WHEN COUNT(i) = 0 THEN 'NOINSCRITO'
            WHEN SUM(i.progreso) = 0 THEN 'INSCRITO'
            WHEN AVG(i.progreso) < 100 THEN 'ENPROGRESO'
            ELSE 'COMPLETADO'
        END
        FROM Inscripcion i
        WHERE i.curso.id = :cursoId
    """)
    String getEstadoProgresoPorIdCurso(@Param("cursoId") Long cursoId);

    @Query(value = """
    SELECT progreso
    FROM inscripcion
    WHERE moduloId = :moduloId
    LIMIT 1
    """, nativeQuery = true)
    Double getProgresoPorModulo(@Param("moduloId") Long moduloId);

    @Modifying
    @Query(value = """
    UPDATE inscripcion
    SET estado = :nuevoEstado, fechaUltimoEstado = CURRENT_TIMESTAMP
    WHERE moduloId = :moduloId
    """, nativeQuery = true)
    int putEstadoProgresoPorIdModulo(@Param("moduloId") Long moduloId,
                                     @Param("nuevoEstado") int nuevoEstado);
}


