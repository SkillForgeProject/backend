package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface EvaluacionMapper {

    @Mapping(target="id_modulo", source = "modulo.id")
    @Mapping(target="id_tipo_evaluacion", source = "tipoEvaluacion.id")
    EvaluacionDto toDto(Evaluacion entidad);
}
