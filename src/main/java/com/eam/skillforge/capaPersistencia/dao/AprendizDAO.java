package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.AprendizRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AprendizDAO {
    private final AprendizRepositorio aprendizRepositorio;
    private final UsuarioMapper aprendizMapper;

    public Optional<UsuarioDto> buscarPorId(Long id) {
        return aprendizRepositorio.findById(id)
                .map(aprendizMapper::toDto);
    }

    public void actualizarProgresoModulo(int usuarioId, Double progreso, int moduloId) {
        aprendizRepositorio.actualizarProgresoModulo(usuarioId, progreso, moduloId);
    }
}
