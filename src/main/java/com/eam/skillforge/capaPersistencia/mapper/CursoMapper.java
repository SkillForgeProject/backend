package com.eam.skillforge.capaPersistencia.mapper;


import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaPersistencia.entidad.Categoria;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CursoMapper {

    @Mapping(target = "nombreCategoria", source = "categoria.nombre")
    CursoDto toDTO(Curso entidad);


    List<CursoDto> toDTOList(List<Curso> entidades);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", source = "id_categoria", qualifiedByName = "crearEntidadCategoriaPorId")
    Curso toEntidad(CursoDto cursoDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarEntidadDesdeDto(CursoDto curso, @MappingTarget Curso entidad);

    /**
     * función auxiliar: Crea la entidad Categoría con solo el ID
     * @param categoriaId: id de la categoría
     * @return La entidad Categoría
     */
    @Named("crearEntidadCategoriaPorId")
    default Categoria crearEntidadCategoriaPorId(Long categoriaId) {
        if(categoriaId == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        return categoria;
    }

    @Named("extraerCategoriaIdDeEntidad")
    default Long extraerCategoriaIdDeEntidad(Categoria categoria) {
        return categoria != null ? categoria.getId() : null;
    }
}
