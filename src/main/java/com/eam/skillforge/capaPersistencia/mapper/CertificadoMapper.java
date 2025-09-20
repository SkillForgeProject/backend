package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.CertificadoDto;
import com.eam.skillforge.capaPersistencia.entidad.Certificado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CertificadoMapper {

    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "nombreCurso", source = "curso.titulo")
    CertificadoDto toDTO(Certificado entidad);


    List<CertificadoDto> toDTOList(List<Certificado> entidades);


    @Mapping(target = "id", ignore = true)
    Certificado toEntidad(CertificadoDto certificadoDto);
}
