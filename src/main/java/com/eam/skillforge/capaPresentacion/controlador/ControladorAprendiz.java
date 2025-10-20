package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.AprendizServicio;
import com.eam.skillforge.capaPersistencia.entidad.Estado;
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
            @ApiResponse(responseCode = "200", description = "Se obtuvo los cursos"),
            @ApiResponse(responseCode = "500", description = "Error al obtener los cursos"),
            @ApiResponse(responseCode = "404", description = "No se encontraron los cursos")
    })
    public ResponseEntity<List<CursoDto>> getCursosPorIdUsuario(@PathVariable Long usuarioId ){
        log.info("GET aprendiz/{id}/cursos - Obteniendo todos cursos por ID: {} de usuario", usuarioId);
        List<CursoDto> cursos = aprendizServicio.getCursosPorIdUsuario(usuarioId);
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("{usuarioId}/certificaciones/cantidad")
    @Operation(summary = "Obtiene la cantidad de certificaciones que un aprendiz ha obtenido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene la cantidad de certificaciones"),
            @ApiResponse(responseCode = "500", description = "Error al obtener las certificaciones"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso")
    })
    public ResponseEntity<Integer> getCantidadCertificaciones(@PathVariable Long usuarioId) {
        log.info("GET aprendiz/{}/certificaciones/cantidad - Obteniendo cantidad de certificaciones", usuarioId);
        Integer cantidad = aprendizServicio.getCantidadCertificaciones(usuarioId);
        log.info("Se encontraron {} cerficaciones con el ID de usuario: {}", cantidad, usuarioId);
        return ResponseEntity.ok(cantidad);

    }

    @GetMapping("{usuarioId}/cursos-completados")
    @Operation(summary = "Obtiene los cursos que un aprendiz ha completado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene los cursos completados"),
            @ApiResponse(responseCode = "500", description = "Error al obtener las certificaciones"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso")
    })
    public ResponseEntity<List<CursoDto>> getCursosCompletadosPorIdUsuario(@PathVariable Long usuarioId) {
        log.info("GET aprendiz/{}/cursos - Obteniendo cursos completados", usuarioId);
        List<CursoDto> cursos = aprendizServicio.getCursosCompletadosPorIdUsuario(usuarioId);
        log.info("Se encontraron {} cursos", cursos.size());
        return ResponseEntity.ok(cursos);
    }

    @PostMapping("/{usuarioId}/solicitud/{cursoId}")
    @Operation(summary = "Crea una solicitud para la inscripción de un aprendiz a un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud creada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al crear la solicitud"),
            @ApiResponse(responseCode = "404", description = "No se encuentra el recurso para crear la solicitud")
    })
    public ResponseEntity<String> postSolicitudInscripcionCurso(@PathVariable Long usuarioId, @PathVariable Long cursoId) {
        log.info("POST aprendiz/{}/solicitud/{} - Creando solicitud de inscripción", usuarioId, cursoId);

        try {
            aprendizServicio.postSolicitudInscripcionCurso(usuarioId, cursoId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Solicitud creada correctamente en estado ENESPERA.");
        } catch (RuntimeException e) {
            log.error("Error al crear la solicitud: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("{cursoId}/estado/progresoCurso")
    @Operation(summary = "Obtiene el estado del progreso de un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al obtener el estado del progreso del curso"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso")
    })
    public ResponseEntity<Estado> getEstadoProgresoPorIdCurso(@PathVariable Long cursoId) {
        log.info("GET aprendiz/{}/estado/progresoCurso Obteniendo estado progreso", cursoId);
        Estado estadoCurso = aprendizServicio.getEstadoProgresoPorIdCurso(cursoId);
        log.info("Se encontro el estado {} del curso con id {}", estadoCurso, cursoId);
        return ResponseEntity.ok(estadoCurso);
    }

    @PutMapping("/modulo/{moduloId}/estado")
    @Operation(summary = "Actualiza el estado del progreso de un módulo según su avance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente"),
            @ApiResponse(responseCode = "500", description = "No se encontró la inscripción"),
            @ApiResponse(responseCode = "404", description = "Error al actualizar el estado")
    })
    public ResponseEntity<Void> putEstadoProgresoPorIdModulo(@PathVariable Long moduloId) {
        log.info("PUT aprendiz/modulo/{}/estado Actualizando estado progreso", moduloId);
        aprendizServicio.putEstadoProgresoPorIdModulo(moduloId);
        log.info("Se actualizo el estado del modulo con id {}", moduloId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/progreso/{moduloId}")
    @Operation(summary = "Obtiene el progreso de un modulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Progreso obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al obtener el progreso del modulo"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso")
    })
    public ResponseEntity<Double> getProgresoPorModulo(@PathVariable Long moduloId) {
        log.info("GET aprendiz/progreso/{} Obteniendo progreso", moduloId);
        Double progreso = aprendizServicio.getProgresoPorModulo(moduloId);
        log.info("Se encontro el progreso {} del modulo con id {}", progreso, moduloId);
        return ResponseEntity.ok(progreso);
    }

}
