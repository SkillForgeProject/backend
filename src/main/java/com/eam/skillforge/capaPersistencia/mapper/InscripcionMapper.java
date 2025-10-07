package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.InscripcionDto;
import com.eam.skillforge.capaPersistencia.entidad.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface InscripcionMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "moduloId", source = "modulo.id")
    @Mapping(target = "estado", source = "estado.id")
    InscripcionDto toDto(Inscripcion entidad);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "crearUsuarioDesdeId")
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "crearCursoDesdeId")
    @Mapping(target = "modulo", source = "moduloId", qualifiedByName = "crearModuloDesdeId")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "crearEstadoDesdeId")
    Inscripcion toEntidad(InscripcionDto dto);


    @Named("crearUsuarioDesdeId")
    default Usuario crearUsuarioDesdeId(Long usuarioId) {
        if(usuarioId == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }

    @Named("crearCursoDesdeId")
    default Curso crearCursoDesdeId(Long cursoId) {
        if(cursoId == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(cursoId);
        return curso;
    }

    @Named("crearModuloDesdeId")
    default Modulo crearModuloDesdeId(Long moduloId) {
        if (moduloId == null) return null;
        Modulo m = new Modulo();
        m.setId(moduloId);
        return m;
    }

    @Named("crearEstadoDesdeId")
    default Estado crearEstadoDesdeId(Long estadoId) {
        if(estadoId == null) {
            return null;
        }
        Estado estado = new Estado();
        estado.setId(estadoId);
        return estado;
    }
}
