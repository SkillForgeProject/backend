package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.EnvioEvaluacionDto;
import com.eam.skillforge.capaNegocio.dto.RespuestaDto;
import com.eam.skillforge.capaNegocio.servicio.RespuestaServicio;
import com.eam.skillforge.capaPersistencia.entidad.Respuesta;
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

@RestController
@RequestMapping("/respuestas")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Respuestas", description = "Operaciones para la gestión de las respuestas")
@CrossOrigin(origins = "*")
public class ControladorRespuesta {

    private final RespuestaServicio respuestaServicio;

    @Operation(
            summary = "Enviar respuesta",
            description = "Enviar una respuesta a una evaluación")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Envío de respuesta satisfactorio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Respuesta.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @PostMapping
    public ResponseEntity<RespuestaDto> enviarRespuesta(
            @RequestBody EnvioEvaluacionDto respuestas) {
        log.info("POST /respuestas - Creando respuesta: {}", respuestas);
        try {
            RespuestaDto respuestaCreada = respuestaServicio.crearRespuesta(respuestas);
            log.info("Respuesta creada satisfactoriamente con ID: {}", respuestaCreada.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuestaCreada);
        } catch (IllegalArgumentException e) {
            log.warn("Error en la validación al crear la respuesta: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.warn("Error interno del servidor: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
