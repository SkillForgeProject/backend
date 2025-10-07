package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import com.eam.skillforge.capaPersistencia.entidad.Modulo;
import com.eam.skillforge.capaPersistencia.entidad.ModuloRecurso;
import com.eam.skillforge.capaPersistencia.entidad.Recurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface ModuloRecursoMapper {

    @Mapping(target = "moduloId", source = "modulo.id")
    @Mapping(target = "recursoId", source = "recurso.id")
    ModuloRecursoDto toDto(ModuloRecurso entidad);

    List<ModuloRecursoDto> toDtoList(List<ModuloRecurso> entidades);


    @Mapping(target = "modulo", source = "moduloId", qualifiedByName = "crearModuloDesdeId")
    @Mapping(target = "recurso", source = "recursoId", qualifiedByName = "crearRecursoDesdeId")
    ModuloRecurso toEntidad(ModuloRecursoDto dto);

    @Named("crearModuloDesdeId")
    default Modulo crearModuloDesdeId(Long moduloId) {
        if(moduloId == null) {
            return null;
        }
        Modulo modulo = new Modulo();
        modulo.setId(moduloId);
        return modulo;
    }

    @Named("crearRecursoDesdeId")
    default Recurso crearRecursoDesdeId(Long recursoId) {
        if(recursoId == null) {
            return null;
        }
         Recurso recurso = new Recurso();
        recurso.setId(recursoId);
        return recurso;
    }
}
