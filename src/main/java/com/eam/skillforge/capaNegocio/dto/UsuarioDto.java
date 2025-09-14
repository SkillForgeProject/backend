package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Rol;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Información de un usuario dentro del sistema"
)
public class UsuarioDto {

    @Schema(
            description = "Identificador único del usuario",
            example = "101", accessMode = Schema.AccessMode.READ_ONLY
    )
    private int id;

    @Schema(
            description = "Contraseña del usuario (debe estar cifrada antes de almacenarse)",
            example = "hashed_password",
            required = true)
    private String contrasena;

    @Schema(description = "Nombre completo del usuario",
            example = "Juan Pérez",
            required = true)
    private String nombre;

    @Schema(
            description = "Correo electrónico del usuario",
            example = "juan.perez@email.com",
            required = true)
    private String email;

    @Schema(
            description = "Rol asignado al usuario (ADMIN=1, INSTRUCTOR=2, USER=3)",
            example = "1",
            required = true)
    private Rol id_rol;

    @Schema(
            description = "Departamento o área a la que pertenece el usuario",
            example = "Ingeniería de Sistemas",
            required = true)
    private String departamento;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getId_rol() {
        return id_rol;
    }
    public void setId_rol(Rol id_rol) {
        this.id_rol = id_rol;
    }

    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
