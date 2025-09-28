package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.excepciones.CorreoNoEncontradoExcepcion;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.UsuarioRepositorio;

public class UsuarioDAO {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDto buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByEmail(correo)
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new CorreoNoEncontradoExcepcion(correo));
    }

}
