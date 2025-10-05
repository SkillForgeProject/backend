package com.eam.skillforge.capaNegocio.excepciones;

public class CorreoNoEncontradoExcepcion extends RuntimeException {

    public CorreoNoEncontradoExcepcion(String correo) {
        super("Usuario con correo " + correo + " no encontrado");
    }
}
