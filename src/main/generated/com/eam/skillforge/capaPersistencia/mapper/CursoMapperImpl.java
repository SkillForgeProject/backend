package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaPersistencia.entidad.Categoria;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-27T22:50:47-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class CursoMapperImpl implements CursoMapper {

    @Override
    public CursoDto toDTO(Curso entidad) {
        if ( entidad == null ) {
            return null;
        }

        CursoDto cursoDto = new CursoDto();

        cursoDto.setNombreCategoria( entidadCategoriaNombre( entidad ) );
        if ( entidad.getId() != null ) {
            cursoDto.setId( entidad.getId().intValue() );
        }
        cursoDto.setTitulo( entidad.getTitulo() );
        cursoDto.setDescripcion( entidad.getDescripcion() );

        return cursoDto;
    }

    @Override
    public List<CursoDto> toDTOList(List<Curso> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<CursoDto> list = new ArrayList<CursoDto>( entidades.size() );
        for ( Curso curso : entidades ) {
            list.add( toDTO( curso ) );
        }

        return list;
    }

    @Override
    public Curso toEntidad(CursoDto cursoDto) {
        if ( cursoDto == null ) {
            return null;
        }

        Curso curso = new Curso();

        curso.setCategoria( crearEntidadCategoriaPorId( cursoDto.getId_categoria() ) );
        curso.setTitulo( cursoDto.getTitulo() );
        curso.setDescripcion( cursoDto.getDescripcion() );

        return curso;
    }

    private String entidadCategoriaNombre(Curso curso) {
        Categoria categoria = curso.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        return categoria.getNombre();
    }
}
