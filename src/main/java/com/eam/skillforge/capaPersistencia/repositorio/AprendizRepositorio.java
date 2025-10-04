package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AprendizRepositorio extends JpaRepository<Usuario, Long> {

    @Query(value = "UPDATE inscripcion SET progreso=:progreso WHERE moduloId=:moduloId AND usuarioId=:usuarioId", nativeQuery = true)
    void actualizarProgresoModulo(@Param("usuarioId") int usuarioId,
                                  @Param("progreso") Double progreso,
                                  @Param("moduloId") int moduloId
                                  );
}
