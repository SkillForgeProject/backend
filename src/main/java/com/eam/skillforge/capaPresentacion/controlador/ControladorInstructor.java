package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

public class ControladorInstructor {
    @GetMapping("/")
    @Operation(summary = "Listar usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios"),
            @ApiResponse(responseCode = "401", description = "Este usuario no es permitido")
    })
    public ResponseEntity<ArrayList<UsuarioDto>> getUsuarios() {
        return new ResponseEntity<>(new ArrayList<UsuarioDto>(), HttpStatus.OK);
    }
}
