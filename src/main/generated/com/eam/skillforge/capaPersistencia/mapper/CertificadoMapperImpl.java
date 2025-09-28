package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.CertificadoDto;
import com.eam.skillforge.capaPersistencia.entidad.Certificado;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-27T22:53:05-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class CertificadoMapperImpl implements CertificadoMapper {

    @Override
    public CertificadoDto toDTO(Certificado entidad) {
        if ( entidad == null ) {
            return null;
        }

        CertificadoDto certificadoDto = new CertificadoDto();

        certificadoDto.setNombreUsuario( entidadUsuarioNombre( entidad ) );
        certificadoDto.setNombreCurso( entidadCursoTitulo( entidad ) );
        if ( entidad.getId() != null ) {
            certificadoDto.setId( entidad.getId().intValue() );
        }
        certificadoDto.setHash( entidad.getHash() );

        return certificadoDto;
    }

    @Override
    public List<CertificadoDto> toDTOList(List<Certificado> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<CertificadoDto> list = new ArrayList<CertificadoDto>( entidades.size() );
        for ( Certificado certificado : entidades ) {
            list.add( toDTO( certificado ) );
        }

        return list;
    }

    @Override
    public Certificado toEntidad(CertificadoDto certificadoDto) {
        if ( certificadoDto == null ) {
            return null;
        }

        Certificado certificado = new Certificado();

        certificado.setHash( certificadoDto.getHash() );

        return certificado;
    }

    private String entidadUsuarioNombre(Certificado certificado) {
        Usuario usuario = certificado.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getNombre();
    }

    private String entidadCursoTitulo(Certificado certificado) {
        Curso curso = certificado.getCurso();
        if ( curso == null ) {
            return null;
        }
        return curso.getTitulo();
    }
}
