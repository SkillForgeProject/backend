package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaNegocio.servicio.ModuloServicio;
import com.eam.skillforge.capaPersistencia.dao.ModuloDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
