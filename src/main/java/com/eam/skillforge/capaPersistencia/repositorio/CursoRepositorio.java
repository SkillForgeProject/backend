package com.eam.skillforge.capaPersistencia.repositorio;

import java.util.List;

import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eam.skillforge.capaPersistencia.entidad.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {

        List<Curso> findByTutorId(Long tutorId);

        @Query("SELECT c FROM Curso c WHERE c.categoria.id = :nivelId")
        List<Curso> findByNivel(Long nivelId);

        @Query(value = """
            SELECT CURS.id, CURS.titulo, AVG(CURS_PUNT.puntuacion) as puntuacion
            FROM cursos_puntuacion AS CURS_PUNT
            INNER JOIN curso AS CURS ON CURS_PUNT.cursoId = CURS.id
            GROUP BY CURS.id, CURS.titulo
            ORDER BY puntuacion DESC
            LIMIT 4
            """, nativeQuery = true)
        List<Object[]> findTopCursosMejoresPuntuados();

        @Query(value = """
            SELECT 
                ( (SELECT COUNT(id) FROM usuario WHERE id_rol = 3) - COUNT(INS.usuarioId) ) AS diferenciaUsuarios,
                CURS.id AS cursoId,
                CURS.titulo
            FROM inscripcion INS
            INNER JOIN curso CURS ON INS.cursoId = CURS.id
            WHERE INS.estado = 3
            GROUP BY CURS.id, CURS.titulo
            ORDER BY diferenciaUsuarios ASC
            LIMIT 6
            """, nativeQuery = true)
    List<Object[]> findCursosMasTomados();

    @Query(value = """
            SELECT DISTINCT U.*
            FROM inscripcion AS INS
            INNER JOIN usuario AS U ON INS.usuarioId = U.id
            WHERE INS.cursoId = :cursoId
            """, nativeQuery = true)
    List<Usuario> findUsuariosPorCursoId(@Param("cursoId") Long cursoId);

    @Query("SELECT c FROM Curso c WHERE c.isActivo = true")
    List<Curso> getCursosActivos();
}
