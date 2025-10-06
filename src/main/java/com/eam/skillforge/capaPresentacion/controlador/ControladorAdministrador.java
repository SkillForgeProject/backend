package com.eam.skillforge.capaPresentacion.controlador;

import com.eam.skillforge.capaNegocio.dto.ReporteCursoDto;
import com.eam.skillforge.capaNegocio.dto.UsuarioDto;
import com.eam.skillforge.capaNegocio.dto.UsuariosPorCursoMesDTO;
import com.eam.skillforge.capaNegocio.servicio.AdministradorServicio;
import com.eam.skillforge.capaNegocio.servicio.ReporteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/administrador")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Administrador")
public class ControladorAdministrador {
    private final AdministradorServicio administradorServicio;
    private final ReporteServicio reporteServicio;

    @PostMapping
    @Operation(summary = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuaro creado"),
            @ApiResponse(responseCode = "500", description = "Creación fallida"),
            @ApiResponse(responseCode = "400", description = "Error en los campos del usuario"),
            @ApiResponse(responseCode = "404", description =  "No se encontró el recurso")
    })
    public ResponseEntity<UsuarioDto> postUsuario(@RequestBody UsuarioDto usuario) {
        UsuarioDto usuarioCreado = administradorServicio.postUsuario(usuario);
        return new ResponseEntity(usuarioCreado, HttpStatus.OK);
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
            @ApiResponse(responseCode = "400", description = "Petición erronea"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error al eliminar el usuario")
    })
    public ResponseEntity deleteUsuario(@PathVariable Long id) {
        administradorServicio.deleteUsuario(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/cantidadEstudiantes")
    @Operation(summary = "Obtener cantidad estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cantidad de estudiantes obtenida correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Integer> getCantidadEstudiantes() {
        Integer cantidadUsuarios = administradorServicio.getCantidadUsuarios();
        return new ResponseEntity(cantidadUsuarios, HttpStatus.OK);
    }

    @GetMapping("/tasaFinalizacion")
    @Operation(summary = "Obtener tasa de finalización")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasa de finalización obtenida correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Double> getTasaFinalizacion() {
        Double tazaFinalizacion = administradorServicio.getTasaFinalizacion();
        return new ResponseEntity(tazaFinalizacion, HttpStatus.OK);
    }

    @GetMapping("/usuariosPorCursoMes")
    @Operation(summary = "Obtener cantidad de usuarios por curso fraccionado por mes")
    public ResponseEntity<List<UsuariosPorCursoMesDTO>> getUsuariosPorCursoMes() {
        List<UsuariosPorCursoMesDTO> usuariosPorCursoMes = administradorServicio.getUsuariosPorCursoMes();
        return new ResponseEntity<>(usuariosPorCursoMes, HttpStatus.OK);
    }

    @GetMapping("/admin/reportes/cursos/rendimiento")
    @Operation(summary = "Métricas administrativas sobre los cursos de la plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos para el reporte"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "403", description = "usuario no autorizado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Page<ReporteCursoDto>> getReporteCurso(
            @RequestParam(required = false) LocalDate fechaDesde,
            @RequestParam(required = false) LocalDate fechaHasta,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Long tutorId,
            @RequestParam(required = false, defaultValue = "0") int pagina,
            @RequestParam(required = false, defaultValue = "20") int tamano
            ) {
        log.info("GET /admin/reportes/cursos/rendimiento?desde={}&hasta={}&categoriaId={}" +
                        "&tutorId={}&pagina={}&tamano={}",
                fechaDesde, fechaHasta, categoriaId, tutorId, pagina, tamano);
        try {
            Page<ReporteCursoDto> reporte = reporteServicio.getRendimientoCursos(
                    fechaDesde, fechaHasta, categoriaId, tutorId, pagina, tamano);
            return ResponseEntity.ok(reporte);
        } catch(IllegalArgumentException e) {
            log.warn("Datos inválidos {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch(Exception e) {
            log.error("Error interno del servidor {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
