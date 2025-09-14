package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Estado;

import java.time.LocalDate;

public class InscripcionDto {
    public int id;
    public int id_curso;
    public float progreso;
    public LocalDate fechaInscripcion;
    public Estado id_estado;
}