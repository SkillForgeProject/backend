package com.eam.skillforge.capaPersistencia.entidad;

public enum Estado {
    NOINSCRITO(1),
    INSCRITO(2),
    ENPROGRESO(3),
    COMPLETADO(4);

    private int id;
    Estado(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
