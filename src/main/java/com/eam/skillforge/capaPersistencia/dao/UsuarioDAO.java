package com.eam.skillforge.capaPersistencia.dao;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.excepciones.CorreoNoEncontradoExcepcion;
import com.eam.skillforge.capaNegocio.excepciones.UsuarioNoEncontradoExcepcion;
import com.eam.skillforge.capaPersistencia.mapper.UsuarioMapper;
import com.eam.skillforge.capaPersistencia.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioDAO {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDto buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByEmail(correo)
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new CorreoNoEncontradoExcepcion(correo));
    }

    public Optional<UsuarioDto> buscarPorId(Long id) {
        return usuarioRepositorio.findById(id)
                .map(usuarioMapper::toDto);
    }

}
