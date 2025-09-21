package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public class ControladorAdministrador {
    @PostMapping("")
    @Operation(summary = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuaro creado"),
            @ApiResponse(responseCode = "500", description = "Creación fallida")
    })
    public ResponseEntity postUsuario(@RequestBody UsuarioDto usuario) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    })
    public ResponseEntity<UsuarioDto> getUsuarioById(@RequestParam String id) {
        return new ResponseEntity<>(new UsuarioDto(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "500", description = "Error al actualizar el usuario")
    })
    public ResponseEntity<UsuarioDto> updateUsuario(@RequestParam String id) {
        return new ResponseEntity<>(new UsuarioDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "500", description = "Error al eliminar el usuario")
    })
    public ResponseEntity deleteUsuario(@RequestParam int id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
