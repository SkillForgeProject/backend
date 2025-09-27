package com.eam.skillforge.capaNegocio.excepciones;

public class UsuarioNoEncontradoExcepcion extends RuntimeException {
    public UsuarioNoEncontradoExcepcion(Long id) {
        super("Usuario con id " + id + " no encontrado");
    }
}