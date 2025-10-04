package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.servicio.CursoServicio;
import com.eam.skillforge.capaPersistencia.dao.CursoDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<CursoDto> buscarCursosPorNivel(Long nivelId) {
        return cursoDAO.buscarPorNivel(nivelId)
                .orElseThrow(() -> {
                    log.warn("Cursos no encontrados por ID de nivel: {}", nivelId);
                    return new RuntimeException("Cursos no encontrados por ID de nivel" + nivelId);
                });
    }

    @Override
    public CursoDto actualizarCurso(Long id, CursoDto curso) {
        log.info("Actualizando curso ID: {}", id);
        getCursoPorId(id);

        validarDataCurso(curso);

        return cursoDAO.actualizar(id, curso)
                .orElseThrow(() -> new RuntimeException("Error al actualizar el curso"));

    }

    private void validarDataCurso(CursoDto curso) {

    }
}
