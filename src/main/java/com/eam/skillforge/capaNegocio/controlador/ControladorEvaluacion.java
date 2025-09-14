package com.eam.skillforge.capaNegocio.controlador;

import com.eam.skillforge.capaPersistencia.entidad.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/modulos")
public class ControladorEvaluacion {

    /*private final ServicioModulo servicio;
    public ControladorCertificado(ServicioModulo servicio) {
        this.servicio = servicio;
    }*/

    @Operation(
            summary = "Enviar respuesta",
            description = "Enviar una respuesta a un examen")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Envío de respuesta satisfactorio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Respuesta.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<Respuesta> enviarRespuesta(
            @RequestParam Long idUsuario,
            @RequestParam Long idCurso,
            @RequestParam Long idEvaluacion) {
        return null;
    }
}
