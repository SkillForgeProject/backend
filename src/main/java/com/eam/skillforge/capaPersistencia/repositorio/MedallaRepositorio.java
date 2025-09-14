package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaPersistencia.entidad.Medalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedallaRepositorio extends JpaRepository<Medalla, Long> {


}

