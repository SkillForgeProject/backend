package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.Modulo;
import com.eam.skillforge.capaPersistencia.entidad.Recurso;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface ModuloMapper {

    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "recursoId", source = "recurso.id")
    ModuloDto toDto(Modulo entidad);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "crearEntidadCursoDesdeId")
    @Mapping(target = "recurso", source = "recursoId", qualifiedByName = "crearEntidadRecursoDesdeId")
    Modulo toEntidad(ModuloDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "crearEntidadCursoDesdeId")
    @Mapping(target = "recurso", source = "recursoId", qualifiedByName = "crearEntidadRecursoDesdeId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarEntidadDesdeDto(ModuloDto dto, @MappingTarget Modulo entidad);

    @Named("crearEntidadCursoDesdeId")
    default Curso crearEntidadCursoDesdeId(Long cursoId) {
        if(cursoId == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(cursoId);
        return curso;
    }

    @Named("crearEntidadRecursoDesdeId")
    default Recurso crearEntidadRecursoDesdeId(Long recursoId) {
        if(recursoId == null) {
            return null;
        }
        Recurso recurso = new Recurso();
        recurso.setId(recursoId);
        return recurso;
    }

}
