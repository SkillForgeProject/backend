package com.eam.skillforge.capaNegocio.controlador;

import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaPersistencia.entidad.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public class ControladorUsuario {

    @GetMapping("/")
    @Operation(summary = "Listar usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios"),
            @ApiResponse(responseCode = "401", description = "Este usuario no es permitido")
    })
    public ResponseEntity<ArrayList<UsuarioDto>> getUsuarios() {
        return new ResponseEntity<>(new ArrayList<UsuarioDto>(), HttpStatus.OK);
    }

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
