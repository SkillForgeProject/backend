package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class ControladorAuth {

    @PostMapping("/login")
    @Operation(summary = "Autenticación de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token JWT generado"),
            @ApiResponse(responseCode = "404", description = "No se encontró el servicio"),
            @ApiResponse(responseCode = "500", description = "Hubo un error generado el JWT")
    })
    public ResponseEntity loggearUsuario(@RequestBody UsuarioDto user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
