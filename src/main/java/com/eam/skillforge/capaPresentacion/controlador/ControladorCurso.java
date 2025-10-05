package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.*;
import com.eam.skillforge.capaNegocio.servicio.CursoServicio;
import com.eam.skillforge.capaPersistencia.entidad.Curso;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.eam.skillforge.capaNegocio.dto.CursoDto;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Curso")
public class ControladorCurso {

    private final CursoServicio cursoServicio;


    @Operation(
            summary = "Crear un nuevo curso",
            description = "Crea un nuevo curso")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Creación de curso ha sido satisfactoria",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<Curso> postCurso(
            @RequestBody Curso curso) {
        return null;
    }

    @Operation(
            summary = "Listar cursos",
            description = "Lista todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Devuelve la lista de cursos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<Curso> listarCursos(
            @RequestParam Curso curso) {
        return null;
    }

    @Operation(
            summary = "Buscar un curso",
            description = "Busca un curso de acuerdo a su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Devuelve el curso encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(
            @RequestParam Integer cursoId) {
        return null;
    }

    @Operation(
            summary = "Eliminar un curso",
            description = "Elimina un curso de acuerdo a su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso eliminado satisfactoriamente",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @DeleteMapping
    public ResponseEntity<Curso> eliminarCurso(
            @RequestParam Integer cursoId) {
        return null;
    }


    @Operation(
            summary = "Actualizar un curso",
            description = "Actualiza un curso de acuerdo a su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Curso actualizado satisfactoriamente",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Curso.class))
            )
    })
    @PutMapping
    public ResponseEntity<Curso> actualizarCurso(
            @RequestParam Integer cursoId,
            @RequestBody Curso curso) {
        return null;
    }

    @Operation(
            summary = "Inscribir un usuario a un curso",
            description = "Inscribe un usuario de acuerdo a su Id y al Id del curso")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "usuario inscrito satisfactoriamente",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))
            )
    })
    @PostMapping("/{id_curso}")
    public ResponseEntity<Curso> inscribirUsuario(
            @RequestParam Integer cursoId,
            @RequestBody Curso curso) {
        return null;
    }

    @GetMapping("/top-mejores-puntuados")
    @Operation(summary = "Obtener los 3 cursos mejor puntuados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de top cursos obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron cursos puntuados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<TopCursoDto>> getTopCursosMejoresPuntuados() {
        log.info("GET /curso/top-mejores-puntuados - Obteniendo los 3 cursos mejor puntuados");
        
        List<TopCursoDto> topCursos = cursoServicio.getTopCursosMejoresPuntuados();
        log.info("Se obtuvieron {} cursos mejor puntuados", topCursos.size());
        
        if (topCursos.isEmpty()) {
            throw new com.eam.skillforge.capaNegocio.excepciones.CursoNoEncontradoExcepcion("No se encontraron cursos con puntuaciones");
        }
        
        return ResponseEntity.ok(topCursos);
    }

    @GetMapping("/cursos-mas-tomados")
    @Operation(summary = "Obtener los 6 cursos más tomados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos con menor diferencia obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron cursos con inscripciones"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CursoDiferenciaUsuariosDto>> getCursosMasTomados() {
        log.info("GET /curso/menor-diferencia-usuarios - Obteniendo cursos con menor diferencia de usuarios");
        
        List<CursoDiferenciaUsuariosDto> cursosConDiferencia = cursoServicio.getCursosMasTomados();
        log.info("Se obtuvieron {} cursos con diferencia de usuarios", cursosConDiferencia.size());
        
        if (cursosConDiferencia.isEmpty()) {
            throw new com.eam.skillforge.capaNegocio.excepciones.CursoNoEncontradoExcepcion("No se encontraron cursos con inscripciones activas");
        }
        
        return ResponseEntity.ok(cursosConDiferencia);
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtiene todos los cursos que se encuentran activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo todos los cursos activos"),
            @ApiResponse(responseCode = "500", description = "Error al obtener los cursos activos"),
            @ApiResponse(responseCode = "404", description = "No se encontraron los cursos activos")
    })
    public ResponseEntity<List<CursoDto>> getCursosActivos(){
        log.info("GET curso/activos - Obteniendo todos cursos activos");
        List<CursoDto> cursos = cursoServicio.getCursosActivos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/estudiantePorCurso/{cursoId}")
    @Operation(
        summary = "Obtener cursos por inscripción",
        description = "Obtiene la información de cursos basada en las inscripciones para un curso específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cursos obtenidos exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron cursos para el ID especificado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<UsuarioDto>> getEstudiantePorIdCurso(
            @Parameter(description = "ID del curso para buscar inscripciones", required = true)
            @PathVariable Long cursoId) {

        log.info("Solicitud para obtener cursos por inscripción del curso ID: {}", cursoId);

        List<UsuarioDto> usuarios = cursoServicio.getCursosPorInscripcion(cursoId);

        log.info("Se encontraron {} cursos por inscripción para el curso ID: {}", usuarios.size(), cursoId);
        return ResponseEntity.ok(usuarios);
    }

}
