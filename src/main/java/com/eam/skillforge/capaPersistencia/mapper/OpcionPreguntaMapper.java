package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.OpcionPreguntaDto;
import com.eam.skillforge.capaPersistencia.entidad.OpcionPregunta;
import com.eam.skillforge.capaPersistencia.entidad.Pregunta;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface OpcionPreguntaMapper {

    // --- ENTIDAD → DTO ---
    @Mapping(target = "idPregunta", source = "pregunta.id")
    OpcionPreguntaDto toDto(OpcionPregunta entidad);

    List<OpcionPreguntaDto> toDtoList(List<OpcionPregunta> entidades);

    // --- DTO → ENTIDAD ---
    @Mapping(target = "pregunta", source = "idPregunta", qualifiedByName = "crearEntidadPreguntaPorId")
    OpcionPregunta toEntidad(OpcionPreguntaDto dto);

    List<OpcionPregunta> toEntidadList(List<OpcionPreguntaDto> dtos);

    // --- Actualizar entidad desde DTO ---
    @Mapping(target = "pregunta", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarEntidadDesdeDto(OpcionPreguntaDto dto, @MappingTarget OpcionPregunta entidad);

    // --- Función auxiliar ---
    @Named("crearEntidadPreguntaPorId")
    default Pregunta crearEntidadPreguntaPorId(Long idPregunta) {
        if (idPregunta == null) {
            return null;
        }
        Pregunta pregunta = new Pregunta();
        pregunta.setId(idPregunta);
        return pregunta;
    }
}