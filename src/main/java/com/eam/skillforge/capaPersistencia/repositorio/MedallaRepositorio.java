package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaPersistencia.entidad.Medalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedallaRepositorio extends JpaRepository<Medalla, Long> {

    List<Medalla> findByUsuarioId(Long usuarioId);

}

