package com.eam.skillforge.capaNegocio.excepciones;

public class DatosInvalidosExcepcion extends RuntimeException {
    public DatosInvalidosExcepcion(String mensaje) {
        super("Datos inválidos: " + mensaje);
    }

    public DatosInvalidosExcepcion(String campo, Object valor) {
        super("Datos inválidos en el campo '" + campo + "': " + valor);
    }
}