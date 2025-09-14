package com.eam.skillforge.capaPersistencia.entidad;

public enum Categoria {
    INDUCCION(1),
    CAPACITACION(2),
    ESPECIALIZACION(3);

    private int id;

    Categoria(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
