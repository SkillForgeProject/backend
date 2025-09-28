package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-27T22:53:05-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDto toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();

        if ( usuario.getId() != null ) {
            usuarioDto.setId( usuario.getId().intValue() );
        }
        usuarioDto.setId_rol( usuario.getIdRol() );
        usuarioDto.setNombre( usuario.getNombre() );
        usuarioDto.setEmail( usuario.getEmail() );
        usuarioDto.setDepartamento( usuario.getDepartamento() );

        return usuarioDto;
    }

    @Override
    public Usuario toEntidad(UsuarioDto dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setIdRol( dto.getId_rol() );
        usuario.setNombre( dto.getNombre() );
        usuario.setEmail( dto.getEmail() );
        usuario.setDepartamento( dto.getDepartamento() );

        return usuario;
    }
}
