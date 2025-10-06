package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.CursoDto;
import com.eam.skillforge.capaNegocio.dto.ModuloRecursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Usuario")
public class ControladorUsuario {

    private final UsuarioServicio usuarioServicio;

    @GetMapping("/{correo}")
    @Operation(summary = "Obtener usuario por correo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado con ese correo"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<UsuarioDto> getUsuarioPorCorreo(@PathVariable String correo) {
        UsuarioDto usuario = usuarioServicio.getUsuarioPorCorreo(correo);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/curso/{nivelId}")
    @Operation(summary = "Obtener los curso por Nivel") // INDUCCION, CAPACITACION, ESPECIALIZACION
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos encontrados exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cursos no encontrados con ese nivel"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CursoDto>> getCursosPorNivel(@PathVariable Long nivelId) {
        log.info("GET usuario/curso/ - Obteniendo todos cursos por nivel ID: {}", nivelId);
        List<CursoDto> cursos = usuarioServicio.getCursosPorNivel(nivelId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/modulo/{moduloId}/recursos")
    @Operation(summary = "Obtener el recurso de ese módulo") // INDUCCION, CAPACITACION, ESPECIALIZACION
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado con el ID del modulo"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ModuloRecursoDto>> getRecursosPorModulo(@PathVariable Long moduloId) {
        log.info("GET /tutor/modulo/{}/recursos - Buscando recursos del módulo", moduloId);
        try {
            List<ModuloRecursoDto> recursos = usuarioServicio.getRecursosPorModulo(moduloId);
            if (recursos.isEmpty()) {
                log.warn("No se encontraron recursos para el módulo ID {}", moduloId);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(recursos);
        } catch (Exception e) {
            log.error("Error al obtener los recursos del módulo {}: {}", moduloId, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}

