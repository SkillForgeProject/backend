package com.eam.skillforge.capaPersistencia.repositorio;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepositorio extends JpaRepository<Usuario, Long> {
}
