package com.eam.skillforge.capaNegocio.excepciones;

public class UsuarioNoAutorizadoExcepcion extends RuntimeException {
    public UsuarioNoAutorizadoExcepcion(String message) {
        super(message);
    }
}
