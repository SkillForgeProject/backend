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
}
