package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.EvaluacionDto;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import com.eam.skillforge.capaPersistencia.entidad.Modulo;
import com.eam.skillforge.capaPersistencia.entidad.TipoEvaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface EvaluacionMapper {

    // --- ENTIDAD → DTO ---
    @Mapping(target = "id_modulo", source = "modulo.id")
    @Mapping(target = "id_tipo_evaluacion", source = "tipoEvaluacion.id")
    EvaluacionDto toDto(Evaluacion entidad);

    // --- DTO → ENTIDAD ---
    @Mapping(target = "modulo", source = "id_modulo", qualifiedByName = "crearEntidadModuloPorId")
    @Mapping(target = "tipoEvaluacion", source = "id_tipo_evaluacion", qualifiedByName = "crearEntidadTipoEvaluacionPorId")
    Evaluacion toEntidad(EvaluacionDto dto);

    // --- FUNCIONES AUXILIARES ---
    @Named("crearEntidadModuloPorId")
    default Modulo crearEntidadModuloPorId(Long idModulo) {
        if (idModulo == null) return null;
        Modulo modulo = new Modulo();
        modulo.setId(idModulo);
        return modulo;
    }

    @Named("crearEntidadTipoEvaluacionPorId")
    default TipoEvaluacion crearEntidadTipoEvaluacionPorId(Long idTipoEvaluacion) {
        if (idTipoEvaluacion == null) return null;
        TipoEvaluacion tipo = new TipoEvaluacion();
        tipo.setId(idTipoEvaluacion);
        return tipo;
    }
}
