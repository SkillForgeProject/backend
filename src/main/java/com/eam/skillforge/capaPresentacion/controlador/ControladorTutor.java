package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.servicio.TutorServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Tutor")
public class ControladorTutor {
    private final TutorServicio tutorServicio;

    @PostMapping
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

}
