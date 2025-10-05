package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.AprendizServicio;
import com.eam.skillforge.capaPersistencia.dao.AprendizDAO;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public void putProgresoModulo(int usuarioId, Double progreso, int moduloId) {
        aprendizDAO.actualizarProgresoModulo(usuarioId, progreso, moduloId);
    }

    @Transactional(readOnly = true)
    public Double getProgresoCurso(int usuarioId, int cursoId) {
        return aprendizDAO.obtenerProgresoCurso(usuarioId, cursoId);
    }

    @Override
    @Transactional
    public List<CursoDto> getCursosPorIdUsuario(Long idUsuario) {
        log.debug("Buscando cursos asociados al usuario con ID: {}", idUsuario);

        List<CursoDto> cursos = aprendizDAO.getCursosPorIdUsuario(idUsuario);
        if (cursos.isEmpty()) {
            log.warn("No se encontraron cursos para el usuario con ID: {}", idUsuario);
        } else {
            log.info("Se encontraron {} cursos para el usuario con ID: {}", cursos.size(), idUsuario);
        }
        return cursos;
    }

    @Override
    public Integer getCantidadCertificaciones(Long usuarioId) {
        log.info("Buscando cantidad de certificaciones con ID de usuario: {}", usuarioId);
        Integer cantidad = aprendizDAO.getCantidadCertificaciones(usuarioId);
        if(cantidad == 0) {
            log.warn("No se encontraron certificacioens para el usuario con ID: {}", usuarioId);
        } else {
            log.info("Se encontraron {} certificaciones para el usuario con ID: {}", cantidad, usuarioId);
        }

        return cantidad;
    }
}
