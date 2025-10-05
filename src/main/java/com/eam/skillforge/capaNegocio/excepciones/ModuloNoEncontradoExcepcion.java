package com.eam.skillforge.capaNegocio.excepciones;

public class ModuloNoEncontradoExcepcion extends RuntimeException {
    
    public ModuloNoEncontradoExcepcion(String message) {
        super(message);
    }
    
    public ModuloNoEncontradoExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}