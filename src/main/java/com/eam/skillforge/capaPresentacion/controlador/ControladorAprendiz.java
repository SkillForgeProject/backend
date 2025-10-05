package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.AprendizServicio;
import com.eam.skillforge.capaPersistencia.entidad.Curso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aprendiz")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Aprendiz")
public class ControladorAprendiz {
    private final AprendizServicio aprendizServicio;

    @PutMapping
    @Operation(summary = "Actualizar progreso del módulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización realizada"),
            @ApiResponse(responseCode = "500", description = "Actualización fallida"),
            @ApiResponse(responseCode = "404", description =  "No se encontró el módulo")
    })
    public ResponseEntity<UsuarioDto> postUsuario(@RequestParam int usuarioId, @RequestParam Double progreso, @RequestParam int moduloId) {
        aprendizServicio.putProgresoModulo(usuarioId, progreso, moduloId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Obtener progreso curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el progreso del curso"),
            @ApiResponse(responseCode = "500", description = "Error al actualizar"),
            @ApiResponse(responseCode = "404", description = "No se encontró el curso")
    })
    public ResponseEntity<Double> getProgresoCurso(@RequestParam int usuarioId, @RequestParam int cursoId ){
        Double progreso = aprendizServicio.getProgresoCurso(usuarioId, cursoId);
        return new ResponseEntity<>(progreso, HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}/cursos")
    @Operation(summary = "Obtiene los cursos a los que pertenece un aprendiz por el id del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo loss cursos"),
            @ApiResponse(responseCode = "500", description = "Error al obtener los cursos"),
            @ApiResponse(responseCode = "404", description = "No se encontraron los cursos")
    })
    public ResponseEntity<List<CursoDto>> getCursosPorIdUsuario(@PathVariable Long usuarioId ){
        log.info("GET aprendiz/{id}/cursos - Obteniendo todos cursos por ID: {} de usuario", usuarioId);
        List<CursoDto> cursos = aprendizServicio.getCursosPorIdUsuario(usuarioId);
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
}
