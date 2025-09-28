package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.excepciones.CorreoNoEncontradoExcepcion;
import com.eam.skillforge.capaNegocio.servicio.UsuarioServicio;
import com.eam.skillforge.capaPersistencia.dao.UsuarioDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioDAO usuarioDAO;

    @Override
    public UsuarioDto getUsuarioPorCorreo(String correo){
        log.info("Buscando usuario por correo: {}", correo);

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }

        UsuarioDto usuarioEncontrado = usuarioDAO.buscarPorCorreo(correo);

        if (usuarioEncontrado == null) {
            log.warn("No se encontró un usuario con el correo: {}", correo);
            throw new CorreoNoEncontradoExcepcion("No se encontró un usuario con el correo: " + correo);
        }

        log.info("Usuario encontrado: {}", usuarioEncontrado.getEmail());
        return usuarioEncontrado;
    }


}



