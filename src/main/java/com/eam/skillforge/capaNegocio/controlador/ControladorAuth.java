package com.eam.skillforge.capaNegocio.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControladorAuth {

    @PostMapping("/register")
    @Operation(summary = "Registro de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado"),
            @ApiResponse(responseCode = "500", description = "No se pudo registrar el usuario")
    })
    public ResponseEntity registrarUsuario() {
        return new ResponseEntity<>(HttpStatus.OK);
    };

    @PostMapping("/login")
    @Operation(summary = "Autenticación de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token JWT generado"),
            @ApiResponse(responseCode = "404", description = "No se encontró el servicio"),
            @ApiResponse(responseCode = "500", description = "Hubo un error generado el JWT")
    })
    public ResponseEntity loggearUsuario() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
