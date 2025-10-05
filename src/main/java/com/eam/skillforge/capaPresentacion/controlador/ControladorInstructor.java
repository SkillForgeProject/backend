package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.TutorServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.util.ArrayList;
import java.util.List;

import com.eam.skillforge.capaNegocio.dto.ModuloDto;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Tutor", description = "Operación de gestión que utiliza el tutor para cursos")
@CrossOrigin(origins = "*")
public class ControladorInstructor {
    private final TutorServicio tutorServicio;

    @GetMapping("/")
    @Operation(summary = "Listar usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios"),
            @ApiResponse(responseCode = "401", description = "Este usuario no es permitido")
    })
    public ResponseEntity<ArrayList<UsuarioDto>> getUsuarios() {
        return new ResponseEntity<>(new ArrayList<UsuarioDto>(), HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener los cursos por ID de tutor",
            description = "Obtiene una lista de los cursos por el ID del tutor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cursos encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CursoDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID inválido de tutor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CursoDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/cursos/{tutorId}")
    public ResponseEntity<List<CursoDto>> getCursosPorTutorId(
            @Parameter(description = "ID del tutor", required = true, example = "6")
            @PathVariable Long tutorId) {
        log.debug("GET tutor/cursos/{} - Buscando cursos", tutorId);
        try {
            List<CursoDto> cursos = tutorServicio.getCursosPorTutorId(tutorId);
            return ResponseEntity.ok(cursos);
        } catch(RuntimeException e) {
            log.warn("Cursos no encontrados con ID: {}", tutorId);
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/cursos/{cursoId}")
    public ResponseEntity<Void> eliminarCurso(
            @Parameter(description = "ID del curso a eliminar", required = true, example = "4")
            @PathVariable Long cursoId
    ) {
        log.info("DELETE tutor/cursos/{} - Eliminando curso", cursoId);
        try {
            tutorServicio.eliminarCurso(cursoId);
            log.info("Curso eliminado exitosamente ID: {}", cursoId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.warn("Curso no encontrado para eliminar ID: {}", cursoId);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/curso")
    @Operation(summary = "Crear curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado"),
            @ApiResponse(responseCode = "500", description = "Creación fallida"),
            @ApiResponse(responseCode = "400", description = "Error en los campos del curso"),
            @ApiResponse(responseCode = "404", description =  "No se encontró el recurso")
    })
    public ResponseEntity<CursoDto> postCurso(@RequestBody CursoDto curso) {
        log.info("POST /tutor - Creando curso: {}", curso);

        try {
            CursoDto cursoCreado = tutorServicio.crearCurso(curso);
            log.info("Curso creado exitosamente con ID: {}", cursoCreado.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreado);
        } catch (IllegalArgumentException e) {
            log.warn("Error en la validación al crear el curso: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/curso/{cursoId}")
    @Operation(summary = "Actualizar un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "400", description = "Error en los campos del curso"),
            @ApiResponse(responseCode = "404", description =  "No se encontró el recurso")
    })
    public ResponseEntity<CursoDto> actualizarCurso(@PathVariable Long cursoId, @RequestBody CursoDto curso) {
        log.info("PUT /tutor/curso/{} - Actualizando curso", cursoId);
        try {
            CursoDto cursoCreado = tutorServicio.actualizarCurso(cursoId, curso);
            log.info("Curso actualizado correctamente ID: {}", cursoId);
            return ResponseEntity.ok(cursoCreado);
        } catch (RuntimeException e) {
            if(e.getMessage().contains("no encontrado")) {
                log.warn("Curso no encontrado para actualizar ID: {}", cursoId);
                return ResponseEntity.notFound().build();
            }
            log.warn("Error al actualizar el curso ID: {}", cursoId);
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/modulos")
    @Operation(summary = "Crear módulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Módulo creado"),
            @ApiResponse(responseCode = "500", description = "Creación fallida"),
            @ApiResponse(responseCode = "400", description = "Error en los campos del módulo"),
            @ApiResponse(responseCode = "404", description =  "No se encontró el recurso")
    })
    public ResponseEntity<ModuloDto> postModulo(@RequestBody ModuloDto modulo) {
        log.info("POST /tutor/modulos - Creando modulo: {}", modulo);

        try {
            ModuloDto moduloCreado = tutorServicio.crearModulo(modulo);
            log.info("Módulo creado exitosamente con ID: {}", moduloCreado.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(moduloCreado);
        } catch (IllegalArgumentException e) {
            log.warn("Error en la validación al crear el modulo: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/modulos/{id}")
    @Operation(
            summary = "Eliminar módulo por ID",
            description = "Elimina un módulo específico usando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Módulo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Módulo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> eliminarModuloPorId(
            @Parameter(description = "ID del módulo a eliminar", required = true)
            @PathVariable Long id) {

        log.info("Solicitud para eliminar módulo con ID: {}", id);

        boolean eliminado = tutorServicio.eliminarModuloPorId(id);

        if (eliminado) {
            log.info("Módulo con ID {} eliminado exitosamente", id);
            return ResponseEntity.ok("Módulo eliminado exitosamente");
        } else {
            log.error("No se pudo eliminar el módulo con ID: {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el módulo");
        }
    }


    @PutMapping("/modulos/{moduloId}")
    @Operation(
            summary = "Acualizar módulo por ID",
            description = "Actualiza un módulo específico usando su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Módulo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Módulo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ModuloDto> putModulo(@PathVariable Long moduloId, @RequestBody ModuloDto modulo) {
        log.info("PUT /tutor/modulos/{} - Actualizando módulo", moduloId);
        try {
            ModuloDto moduloActualizado = tutorServicio.actualizarModulo(moduloId, modulo);
            log.info("Módulo actualizado exitosamente ID: {}", moduloId);
            return ResponseEntity.ok(moduloActualizado);
        } catch (RuntimeException e) {
            if(e.getMessage().contains("no encontrado")) {
                log.warn("Módulo no encontrado para actualizar ID: {}", moduloId);
                return ResponseEntity.notFound().build();
            }
            log.warn("Error al actualizar módulo ID: {}: {}", moduloId, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
