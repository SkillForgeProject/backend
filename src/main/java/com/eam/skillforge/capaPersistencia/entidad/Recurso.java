package com.eam.skillforge.capaPersistencia.entidad;

public enum Recurso {
    VIDEO(1),
    TEXTO(2),
    PRACTICA(3);

    private int recurso;

    Recurso(int recurso) {
        this.recurso = recurso;
    }

    public int getRecurso() {
        return recurso;
    }
}
