package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaPersistencia.entidad.Curso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class ControladorCurso {

    /*private final ServicioCurso servicio;
    public ControladorCertificado(ServicioCurso servicio) {
        this.servicio = servicio;
    }*/


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


}
