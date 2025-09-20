package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaPersistencia.entidad.Medalla;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface MedallaMapper {

    //@Mapping(target = "nombreCategoria", source = "Categoria.nombre")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "idUsuario", source = "usuario.id")
    MedallaDto toDto(Medalla entidad);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "idUsuario", qualifiedByName = "crearEntidadUsuarioDesdeId")
    Medalla toEntidad(MedallaDto medallaDto);

    @Named("crearEntidadUsuarioDesdeId")
    default Usuario crearEntidadUsuarioDesdeId(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }
}
