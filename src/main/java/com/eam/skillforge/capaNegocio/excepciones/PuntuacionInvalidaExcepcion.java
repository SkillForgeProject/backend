package com.eam.skillforge.capaNegocio.excepciones;

public class PuntuacionInvalidaExcepcion extends RuntimeException {
    public PuntuacionInvalidaExcepcion(Float puntuacion) {
        super("Puntuación inválida: " + puntuacion + ". La puntuación debe estar entre 0.0 y 5.0");
    }

    public PuntuacionInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}