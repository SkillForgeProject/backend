package com.eam.skillforge.capaPersistencia.mapper;

import com.eam.skillforge.capaNegocio.dto.CursosPuntuacionDto;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import com.eam.skillforge.capaPersistencia.entidad.CursosPuntuacion;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CursosPuntuacionMapper {

    // -------- ENTIDAD -> DTO ----------
    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "tituloCurso", source = "curso.titulo")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    CursosPuntuacionDto toDto(CursosPuntuacion entidad);

    List<CursosPuntuacionDto> toDtoList(List<CursosPuntuacion> entidades);

    // -------- DTO -> ENTIDAD ----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "crearEntidadCursoPorId")
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "crearEntidadUsuarioPorId")
    CursosPuntuacion toEntidad(CursosPuntuacionDto dto);

    List<CursosPuntuacion> toEntidadList(List<CursosPuntuacionDto> dtos);

    /**
     * Función auxiliar: Crea la entidad Curso con solo el ID
     * @param cursoId: id del curso
     * @return La entidad Curso
     */
    @Named("crearEntidadCursoPorId")
    default Curso crearEntidadCursoPorId(Long cursoId) {
        if (cursoId == null) {
            return null;
        }

        Curso curso = new Curso();
        curso.setId(cursoId);
        return curso;
    }

    /**
     * Función auxiliar: Crea la entidad Usuario con solo el ID
     * @param usuarioId: id del usuario
     * @return La entidad Usuario
     */
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