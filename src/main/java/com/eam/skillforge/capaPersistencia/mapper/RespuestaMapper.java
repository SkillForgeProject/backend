package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import com.eam.skillforge.capaPersistencia.entidad.Respuesta;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RespuestaMapper {

    @Mapping(target = "id_evaluacion", source = "evaluacion.id")
    @Mapping(target = "id_usuario", source = "usuario.id")
    RespuestaDto toDto(Respuesta entidad);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evaluacion", source = "id_evaluacion", qualifiedByName = "crearEntidadEvaluacionPorId")
    @Mapping(target = "usuario", source = "id_usuario", qualifiedByName = "crearEntidadUsuarioPorId")
    Respuesta toEntidad(RespuestaDto dto);


    @Named("crearEntidadEvaluacionPorId")
    default Evaluacion crearEntidadEvaluacionPorId(Long evaluacionId) {
        if(evaluacionId == null) {
            return null;
        }

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(evaluacionId);
        return evaluacion;
    }

    @Named("crearEntidadUsuarioPorId")
    default Usuario crearEntidadUsuarioPorId(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }
}
