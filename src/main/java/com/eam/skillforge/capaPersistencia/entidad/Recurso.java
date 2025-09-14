package com.eam.skillforge.capaPersistencia.entidad;

public enum Recurso {
    VIDEO("Video"),
    TEXTO("Texto"),
    QUIZ("Quiz");
    private String recurso;
    Recurso(String recurso) {
        this.recurso = recurso;
    }

    public String getRecurso() {
        return recurso;
    }
}
