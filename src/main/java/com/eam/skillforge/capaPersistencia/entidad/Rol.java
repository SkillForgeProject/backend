package com.eam.skillforge.capaPersistencia.entidad;

public enum Rol {
    ADMIN("ADMIN"),
    INSTRUCTOR("INSTRUCTOR"),
    USER("USER");
    private String rol;

    Rol(String rol) {
        this.rol = rol;
    }

    public String getRold() {
        return rol;
    }
}
