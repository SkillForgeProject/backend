package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Departamento;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // -------- ENTIDAD -> DTO ----------
    @Mapping(target = "id", source = "id") // Long -> int (MapStruct hace cast solo)
    @Mapping(target = "contrasena", ignore = true) // no existe en la entidad
    @Mapping(target = "id_rol", source = "idRol") // el nombre en DTO difiere
    UsuarioDto toDto(Usuario usuario);

    // -------- DTO -> ENTIDAD ----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idRol", source = "id_rol")
    Usuario toEntidad(UsuarioDto dto);
}