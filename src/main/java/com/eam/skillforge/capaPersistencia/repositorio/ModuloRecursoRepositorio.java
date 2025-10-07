package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.ModuloRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuloRecursoRepositorio extends JpaRepository<ModuloRecurso, Long> {

    List<ModuloRecurso> findByModulo_Id(Long moduloId);
}
