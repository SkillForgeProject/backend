package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaPersistencia.entidad.Medalla;
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
public class MedallaMapperImpl implements MedallaMapper {

    @Override
    public MedallaDto toDto(Medalla entidad) {
        if ( entidad == null ) {
            return null;
        }

        MedallaDto medallaDto = new MedallaDto();

        medallaDto.setNombreUsuario( entidadUsuarioNombre( entidad ) );
        medallaDto.setIdUsuario( entidadUsuarioId( entidad ) );
        medallaDto.setId( entidad.getId() );
        medallaDto.setNombre( entidad.getNombre() );
        medallaDto.setCriterio( entidad.getCriterio() );
        medallaDto.setIcono( entidad.getIcono() );

        return medallaDto;
    }

    @Override
    public List<MedallaDto> toDtoList(List<Medalla> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<MedallaDto> list = new ArrayList<MedallaDto>( entidades.size() );
        for ( Medalla medalla : entidades ) {
            list.add( toDto( medalla ) );
        }

        return list;
    }

    @Override
    public Medalla toEntidad(MedallaDto medallaDto) {
        if ( medallaDto == null ) {
            return null;
        }

        Medalla medalla = new Medalla();

        medalla.setUsuario( crearEntidadUsuarioDesdeId( medallaDto.getIdUsuario() ) );
        medalla.setNombre( medallaDto.getNombre() );
        medalla.setCriterio( medallaDto.getCriterio() );
        medalla.setIcono( medallaDto.getIcono() );

        return medalla;
    }

    private String entidadUsuarioNombre(Medalla medalla) {
        Usuario usuario = medalla.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getNombre();
    }

    private Long entidadUsuarioId(Medalla medalla) {
        Usuario usuario = medalla.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
