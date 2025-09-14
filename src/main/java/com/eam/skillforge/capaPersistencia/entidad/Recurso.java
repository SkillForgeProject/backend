package com.eam.skillforge.capaPersistencia.entidad;

public enum Recurso {
    VIDEO(1),
    TEXTO(2),
    QUIZ(3);
    private int id;
    Recurso(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
