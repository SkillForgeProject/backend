package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.AprendizServicio;
import com.eam.skillforge.capaPersistencia.dao.AprendizDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AprendizServicioImpl implements AprendizServicio {
    private final AprendizDAO aprendizDAO;

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getUsuarioPorId(Long id) {
        log.debug("Buscando usuario por ID: {}", id);

        return aprendizDAO.buscarPorId(id)
                .orElseThrow(() -> {
                    log.warn("Usuario no encontrado por ID: {}", id);
                    return new RuntimeException("Usuario no encontrado con ID: " + id);
                });
    }

    public void putProgresoModulo(int usuarioId,Double progreso, int moduloId) {
        aprendizDAO.actualizarProgresoModulo(usuarioId, progreso, moduloId);
    }


}
