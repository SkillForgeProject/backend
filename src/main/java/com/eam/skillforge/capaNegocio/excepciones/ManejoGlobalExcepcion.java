package com.eam.skillforge.capaNegocio.excepciones;

import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ManejoGlobalExcepcion {
    // ==================== EXCEPCIONES DE USUARIO ====================
    @ExceptionHandler(UsuarioNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarUsuarioNoEncontrado(UsuarioNoEncontradoExcepcion ex) {
        log.warn("Usuario no encontrado: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Usuario no encontrado", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CreacionUsuarioExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarCreacionUsuario(CreacionUsuarioExcepcion ex) {
        log.warn("Error en creación de usuario: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Error en datos de usuario", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CorreoNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarCorreoNoEncontrado(CorreoNoEncontradoExcepcion ex) {
        log.warn("Correo no encontrado: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Correo no encontrado", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UsuarioNoAutorizadoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarUsuarioNoAutorizado(UsuarioNoAutorizadoExcepcion ex) {
        log.warn("Usuario no autorizado: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Acceso no autorizado", HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    // ==================== EXCEPCIONES DE CURSOS ====================
    @ExceptionHandler(CursoNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarCursoNoEncontrado(CursoNoEncontradoExcepcion ex) {
        log.warn("Curso no encontrado: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Curso no encontrado", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(PuntuacionInvalidaExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarPuntuacionInvalida(PuntuacionInvalidaExcepcion ex) {
        log.warn("Puntuación inválida: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Puntuación inválida", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DatosInvalidosExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarDatosInvalidos(DatosInvalidosExcepcion ex) {
        log.warn("Datos inválidos: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Datos inválidos", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RecursoYaExisteExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarRecursoYaExiste(RecursoYaExisteExcepcion ex) {
        log.warn("Recurso ya existe: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Recurso duplicado", HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // ==================== EXCEPCIONES DE VALIDACIÓN ====================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String>> manejarArgumentoIlegal(IllegalArgumentException ex) {
        log.warn("Argumento ilegal: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Argumento inválido", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<String>> manejarValidacionArgumentos(MethodArgumentNotValidException ex) {
        StringBuilder errores = new StringBuilder("Errores de validación: ");
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errores.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ")
        );
        
        log.warn("Error de validación: {}", errores.toString());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(errores.toString(), "Error de validación", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse<String>> manejarTipoArgumentoIncorrecto(MethodArgumentTypeMismatchException ex) {
        String mensaje = String.format("El parámetro '%s' con valor '%s' no puede ser convertido al tipo %s", 
            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        
        log.warn("Error de tipo de argumento: {}", mensaje);
        ErrorResponse<String> errorResponse = new ErrorResponse<>(mensaje, "Tipo de parámetro incorrecto", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse<String>> manejarMensajeNoLegible(HttpMessageNotReadableException ex) {
        String mensaje = "Error en el formato del JSON o datos de entrada inválidos";
        log.warn("Mensaje no legible: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(mensaje, "Formato de datos inválido", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse<String>> manejarViolacionIntegridad(DataIntegrityViolationException ex) {
        String mensaje = "Error de integridad de datos. Verifique que los datos no violen las restricciones de la base de datos";
        log.error("Violación de integridad de datos: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(mensaje, "Error de integridad de datos", HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse<String>> manejarErrorSQL(SQLException ex) {
        String mensaje = "Error en la base de datos. Por favor, contacte al administrador del sistema";
        log.error("Error SQL: {}", ex.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(mensaje, "Error de base de datos", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // ==================== EXCEPCIONES GENÉRICAS ====================
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse<String>> manejarRuntimeException(RuntimeException ex) {
        log.error("Runtime exception: {}", ex.getMessage(), ex);
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage(), "Error en tiempo de ejecución", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> manejarExcepcionGeneral(Exception ex) {
        String mensaje = "Ha ocurrido un error inesperado. Por favor, contacte al administrador del sistema";
        log.error("Excepción no controlada: {}", ex.getMessage(), ex);
        ErrorResponse<String> errorResponse = new ErrorResponse<>(mensaje, "Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ModuloNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarModuloNoEncontrado(ModuloNoEncontradoExcepcion ex) {
        log.warn("Módulo no encontrado: {}", ex.getMessage());

        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                "Módulo no encontrado",
                "No se encontró un módulo con el ID especificado",
                HttpStatus.NOT_FOUND.value()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public static class ErrorResponse<T> {
        private T result;
        private boolean success = false;
        private String error;
        private String timestamp;
        private int status;

        public ErrorResponse(T result) {
            this.result = result;
            this.error = result != null ? result.toString() : "Error desconocido";
            this.timestamp = java.time.LocalDateTime.now().toString();
        }

        public ErrorResponse(T result, int status) {
            this(result);
            this.status = status;
        }

        public ErrorResponse(T result, String error, int status) {
            this.result = result;
            this.error = error;
            this.status = status;
            this.timestamp = java.time.LocalDateTime.now().toString();
        }

        // Getters
        public T getResult() {
            return result;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getError() {
            return error;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        // Setters
        public void setResult(T result) {
            this.result = result;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setError(String error) {
            this.error = error;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}