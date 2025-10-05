package com.eam.skillforge.capaNegocio.dto;

import com.eam.skillforge.capaPersistencia.entidad.Departamento;
import com.eam.skillforge.capaPersistencia.entidad.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Información de un usuario dentro del sistema"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    @Schema(
            description = "Identificador único del usuario",
            example = "101", accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

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
    private Departamento departamento;
}
