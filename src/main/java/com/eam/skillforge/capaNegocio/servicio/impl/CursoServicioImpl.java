package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.servicio.CursoServicio;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CursoServicioImpl implements CursoServicio {

    private final CursoDAO cursoDAO;

    @Override
    @Transactional(readOnly = true)
    public CursoDto getCursoPorId(Long cursoId) {
        log.debug("Buscando curso por ID: {}", cursoId);

        return cursoDAO.buscarPorId(cursoId)
                .orElseThrow(() -> {
                    log.warn("Curso no encontrado con ID: {}", cursoId);
                    return new RuntimeException("Producto con encontrado con ID: " + cursoId);
                });
    }
}
