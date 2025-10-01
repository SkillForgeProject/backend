package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.servicio.AprendizServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "201", description = "Actualización realizada"),
            @ApiResponse(responseCode = "500", description = "Actualización fallida"),
            @ApiResponse(responseCode = "404", description =  "No se encontró el módulo")
    })
    public ResponseEntity<UsuarioDto> postUsuario(@RequestParam int usuarioId, @RequestParam Double progreso, @RequestParam int moduloId) {
        aprendizServicio.putProgresoModulo(usuarioId, progreso, moduloId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
