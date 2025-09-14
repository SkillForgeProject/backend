package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepositorio extends JpaRepository<Evaluacion, Long> {


}



