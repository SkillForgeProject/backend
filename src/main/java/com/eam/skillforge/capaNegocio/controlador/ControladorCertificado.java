package com.eam.skillforge.capaNegocio.controlador;

import com.eam.skillforge.capaPersistencia.entidad.Certificado;
import com.eam.skillforge.capaPersistencia.entidad.Evaluacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@Tag(name = "Certificados", description = "Operaciones para genera o validar certificados de cada curso")
public class ControladorCertificado {

    /*private final ServicioCertificado servicio;
    public ControladorCertificado(ServicioCertificado servicio) {
        this.servicio = servicio;
    }*/

    @Operation(
            summary = "Crear una nueva evaluación",
            description = "Crea una nueva evaluación para un módulo de un curso")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Evaluación creada correctamente",
                content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Certificado> crearEvaluacion(
            @RequestParam Long idUsuario,
            @RequestParam Long idCurso) {
        return null;
    }

}
