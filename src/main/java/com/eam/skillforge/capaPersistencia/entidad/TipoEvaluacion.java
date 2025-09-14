package com.eam.skillforge.capaPersistencia.entidad;

public enum TipoEvaluacion {
    MCQ(1),
    ABIERTA(2),
    CERRADA(3);

    private int id;
    TipoEvaluacion(int id) {
        this.id = id;
    }
}
