package com.eam.skillforge.capaNegocio.mapper;

import com.eam.skillforge.capaNegocio.dto.PreguntaDto;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import com.eam.skillforge.capaPersistencia.entidad.Pregunta;
import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface PreguntaMapper {

    // --- ENTIDAD → DTO ---
    @Mapping(target = "idEvaluacion", source = "evaluacion", qualifiedByName = "extraerIdEvaluacionDeEntidad")
    PreguntaDto toDTO(Pregunta entidad);

    List<PreguntaDto> toDTOList(List<Pregunta> entidades);

    // --- DTO → ENTIDAD ---
    @Mapping(target = "evaluacion", source = "idEvaluacion", qualifiedByName = "crearEntidadEvaluacionPorId")
    Pregunta toEntidad(PreguntaDto dto);

    List<Pregunta> toEntidadList(List<PreguntaDto> dtos);

    // --- Actualizar entidad existente desde DTO ---
    @Mapping(target = "evaluacion", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarEntidadDesdeDto(PreguntaDto dto, @MappingTarget Pregunta entidad);

    // --- Funciones auxiliares ---
    @Named("crearEntidadEvaluacionPorId")
    default Evaluacion crearEntidadEvaluacionPorId(Long idEvaluacion) {
        if (idEvaluacion == null) {
            return null;
        }
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(idEvaluacion);
        return evaluacion;
    }

    @Named("extraerIdEvaluacionDeEntidad")
    default Long extraerIdEvaluacionDeEntidad(Evaluacion evaluacion) {
        return evaluacion != null ? evaluacion.getId() : null;
    }
}
