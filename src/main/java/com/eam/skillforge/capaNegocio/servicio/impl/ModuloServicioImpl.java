package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaNegocio.servicio.ModuloServicio;
import com.eam.skillforge.capaPersistencia.dao.ModuloDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ModuloServicioImpl implements ModuloServicio {
    private final ModuloDAO moduloDAO;

    @Override
    public ModuloDto guardarModulo(ModuloDto modulo) {
        ModuloDto moduloCreado = moduloDAO.guardar(modulo);
        log.info("Módulo creado exitosamente {}", moduloCreado);

        return moduloCreado;
    }

    public boolean eliminarModuloPorId(Long id) {
        log.info("Iniciando eliminación de módulo con ID: {}", id);
        
        try {
            boolean resultado = moduloDAO.eliminarModuloPorId(id);
            log.info("Módulo con ID {} eliminado exitosamente", id);
            return resultado;
        } catch (Exception e) {
            log.error("Error al eliminar módulo con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public ModuloDto getModuloPorId(Long id) {
        log.debug("Buscando módulo por ID: {}", id);
        return moduloDAO.buscarPorId(id)
                .orElseThrow(() -> {
                    log.warn("Módulo no encontrado con ID: {}", id);
                    return new RuntimeException("Módulo no encontrado con ID: " + id);
                });
    }

    @Override
    public List<ModuloDto> getModulosPorCursoId(Long cursoId) {
        log.debug("Buscando módulos por cursoId: {}", cursoId);
        return moduloDAO.getModulosPorCursoId(cursoId);
    }
}
