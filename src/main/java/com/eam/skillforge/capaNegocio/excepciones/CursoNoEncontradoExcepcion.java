package com.eam.skillforge.capaNegocio.excepciones;

public class CursoNoEncontradoExcepcion extends RuntimeException {
    public CursoNoEncontradoExcepcion(Long cursoId) {
        super("Curso no encontrado con ID: " + cursoId);
    }

    public CursoNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
}