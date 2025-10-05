package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.MedallaDto;
import com.eam.skillforge.capaNegocio.servicio.MedallaServicio;
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
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medalla")
@RequiredArgsConstructor
@Slf4j
@Tag(
        name = "Medalla",
        description = "CRUD para las medallas"
)
@CrossOrigin(origins = "*")
public class MedallaControlador {
    private final MedallaServicio medallaServicio;

    /**
     * Crear una medalla nueva
     */
    @PostMapping
    @Operation(
            summary = "Crear una medalla nueva",
            description = "Crea una nueva medalla asociada a un usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Medalla creada satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedallaDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o usuario no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    public ResponseEntity<MedallaDto> crearMedalla(
            @Parameter(description = "Datos necesarios para crear una medalla", required = true)
            @RequestBody MedallaDto medallaDto
    ) {
        log.info("POST /medalla - Creando medalla {}", medallaDto.getNombre());

        try {
            MedallaDto medallaCreada = medallaServicio.crearMedalla(medallaDto);
            log.info("Medalla creada satisfactoriamente con ID: {}", medallaCreada.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(medallaCreada);
        } catch (IllegalArgumentException e) {
            log.warn("Error de validación al crear una medalla: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.warn("Error interno del servidor");
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Buscar todas las medalla por ID de usuadio",
            description = "Devuelve todas las medallas relacionadas a un ID de un usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medallas encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedallaDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<MedallaDto>> buscarMedallaPorIdUsuario(
            @Parameter(description = "ID del usuario para buscar medallas", required = true)
            @PathVariable Long id
    ) {
        log.debug("GET /skillforge/medallas/{} Buscando medallas por ID usuario", id);

        try {
            List<MedallaDto> medallas = medallaServicio.getMedallasPorUsuarioId(id);
            return ResponseEntity.ok(medallas);
        } catch(RuntimeException e) {
            log.warn("Medalla no encontrada con ID de usuario: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}














