package com.eam.skillforge.capaNegocio.excepciones;

public class CursoNoEncontradoExcepcion extends RuntimeException {
    
    public CursoNoEncontradoExcepcion(Long cursoId) {
        super("No se encontró el curso con ID: " + cursoId);
    }
    
    public CursoNoEncontradoExcepcion(String message) {
        super(message);
    }
    
    public CursoNoEncontradoExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}