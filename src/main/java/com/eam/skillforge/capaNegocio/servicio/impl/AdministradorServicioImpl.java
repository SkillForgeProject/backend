package com.eam.skillforge.capaNegocio.servicio.impl;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.AdministradorServicio;
import com.eam.skillforge.capaPersistencia.dao.AdministradorDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdministradorServicioImpl implements AdministradorServicio {
    private final AdministradorDAO administradorDAO;

    @Override
    public UsuarioDto postUsuario(UsuarioDto usuario) {
        System.out.println("Se inicia guardado");
        UsuarioDto usuarioCreado =administradorDAO.guardarUsuario(usuario);
        return usuarioCreado;
    }
}
