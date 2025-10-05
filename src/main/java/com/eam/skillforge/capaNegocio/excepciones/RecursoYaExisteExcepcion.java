package com.eam.skillforge.capaNegocio.excepciones;

public class RecursoYaExisteExcepcion extends RuntimeException {
    public RecursoYaExisteExcepcion(String recurso, Object valor) {
        super("El recurso '" + recurso + "' ya existe con el valor: " + valor);
    }

    public RecursoYaExisteExcepcion(String mensaje) {
        super(mensaje);
    }
}