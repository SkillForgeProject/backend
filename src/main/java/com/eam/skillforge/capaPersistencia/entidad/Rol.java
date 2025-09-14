package com.eam.skillforge.capaPersistencia.entidad;

public enum Rol {
    ADMIN(1),
    INSTRUCTOR(2),
    USER(3);
    private int id;

    Rol(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
