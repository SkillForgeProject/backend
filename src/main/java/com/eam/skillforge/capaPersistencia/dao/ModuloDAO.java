package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaPersistencia.entidad.Modulo;
import com.eam.skillforge.capaPersistencia.mapper.ModuloMapper;
import com.eam.skillforge.capaPersistencia.repositorio.ModuloRepositorio;
import com.eam.skillforge.capaNegocio.excepciones.ModuloNoEncontradoExcepcion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Component
@Slf4j
public class ModuloDAO {
    private final ModuloRepositorio moduloRepositorio;
    private final ModuloMapper moduloMapper;

    public ModuloDto guardar(ModuloDto modulo) {
        Modulo entidad = moduloMapper.toEntidad(modulo);
        Modulo entidadGuardada = moduloRepositorio.save(entidad);
        return moduloMapper.toDto(entidadGuardada);
    }

    /**
     * Elimina un módulo por su ID
     * @param id ID del módulo a eliminar
     * @return true si se eliminó correctamente
     * @throws ModuloNoEncontradoExcepcion si el módulo no existe
     */
    public boolean eliminarModuloPorId(Long id) {
        log.info("Eliminando módulo con ID: {}", id);
        
        // Verificar si el módulo existe
        if (!moduloRepositorio.existeModuloPorId(id)) {
            log.warn("Intento de eliminar módulo inexistente con ID: {}", id);
            throw new ModuloNoEncontradoExcepcion("No se encontró el módulo con ID: " + id);
        }

        moduloRepositorio.eliminarRespuestaPorModulo(id);
        moduloRepositorio.eliminarInscripcionesPorModulo(id);
        moduloRepositorio.eliminarEvaluacionPorModulo(id);
        moduloRepositorio.eliminarModuloPorId(id);

       return true;
    }
}
